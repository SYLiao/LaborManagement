/*
 * Copyright 2002-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.labormanagment.java.security.hierarchiroles;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


public class RoleHierarchyImpl implements RoleHierarchy {

	private static final Log logger = LogFactory.getLog(RoleHierarchyImpl.class);

	
	private String roleHierarchyStringRepresentation = null;

	
	private Map<String, Set<GrantedAuthority>> rolesReachableInOneStepMap = null;

	private Map<String, Set<GrantedAuthority>> rolesReachableInOneOrMoreStepsMap = null;


	public void setHierarchy(String roleHierarchyStringRepresentation) {
		this.roleHierarchyStringRepresentation = roleHierarchyStringRepresentation;

		if (logger.isDebugEnabled()) {
			logger.debug("setHierarchy() - The following role hierarchy was set: "
					+ roleHierarchyStringRepresentation);
		}

		buildRolesReachableInOneStepMap();
		buildRolesReachableInOneOrMoreStepsMap();
	}

	@Override
	public Collection<GrantedAuthority> getReachableGrantedAuthorities(
			Collection<? extends GrantedAuthority> authorities) {
		if (authorities == null || authorities.isEmpty()) {
			return AuthorityUtils.NO_AUTHORITIES;
		}

		Set<GrantedAuthority> reachableRoles = new HashSet<>();
		Set<String> processedNames = new HashSet<>();

		for (GrantedAuthority authority : authorities) {
			// Do not process authorities without string representation
			if (authority.getAuthority() == null) {
				reachableRoles.add(authority);
				continue;
			}
			// Do not process already processed roles
			if (!processedNames.add(authority.getAuthority())) {
				continue;
			}
			// Add original authority
			reachableRoles.add(authority);
			// Add roles reachable in one or more steps
			Set<GrantedAuthority> lowerRoles = this.rolesReachableInOneOrMoreStepsMap.get(authority.getAuthority());
			if (lowerRoles == null) {
				continue; // No hierarchy for the role
			}
			for (GrantedAuthority role : lowerRoles) {
				if (processedNames.add(role.getAuthority())) {
					reachableRoles.add(role);
				}
			}
		}

		if (logger.isDebugEnabled()) {
			logger.debug("getReachableGrantedAuthorities() - From the roles "
					+ authorities + " one can reach " + reachableRoles
					+ " in zero or more steps.");
		}

		List<GrantedAuthority> reachableRoleList = new ArrayList<>(reachableRoles.size());
		reachableRoleList.addAll(reachableRoles);

		return reachableRoleList;
	}


	private void buildRolesReachableInOneStepMap() {
		this.rolesReachableInOneStepMap = new HashMap<>();
		for (String line : this.roleHierarchyStringRepresentation.split("\n")) {
			// Split on > and trim excessive whitespace
			String[] roles = line.trim().split("\\s+>\\s+");

			for (int i = 1; i < roles.length; i++) {
				String higherRole = roles[i - 1];
				GrantedAuthority lowerRole = new SimpleGrantedAuthority(roles[i]);

				Set<GrantedAuthority> rolesReachableInOneStepSet;
				if (!this.rolesReachableInOneStepMap.containsKey(higherRole)) {
					rolesReachableInOneStepSet = new HashSet<>();
					this.rolesReachableInOneStepMap.put(higherRole, rolesReachableInOneStepSet);
				} else {
					rolesReachableInOneStepSet = this.rolesReachableInOneStepMap.get(higherRole);
				}
				rolesReachableInOneStepSet.add(lowerRole);

				if (logger.isDebugEnabled()) {
					logger.debug("buildRolesReachableInOneStepMap() - From role " + higherRole
							+ " one can reach role " + lowerRole + " in one step.");
				}
			}
		}
	}


	private void buildRolesReachableInOneOrMoreStepsMap() {
		this.rolesReachableInOneOrMoreStepsMap = new HashMap<>();
		// iterate over all higher roles from rolesReachableInOneStepMap
		for (String roleName : this.rolesReachableInOneStepMap.keySet()) {
			Set<GrantedAuthority> rolesToVisitSet = new HashSet<>(this.rolesReachableInOneStepMap.get(roleName));
			Set<GrantedAuthority> visitedRolesSet = new HashSet<>();

			while (!rolesToVisitSet.isEmpty()) {
				// take a role from the rolesToVisit set
				GrantedAuthority lowerRole = rolesToVisitSet.iterator().next();
				rolesToVisitSet.remove(lowerRole);
				if (!visitedRolesSet.add(lowerRole) ||
						!this.rolesReachableInOneStepMap.containsKey(lowerRole.getAuthority())) {
					continue; // Already visited role or role with missing hierarchy
				} else if (roleName.equals(lowerRole.getAuthority())) {
					throw new CycleInRoleHierarchyException();
				}
				rolesToVisitSet.addAll(this.rolesReachableInOneStepMap.get(lowerRole.getAuthority()));
			}
			this.rolesReachableInOneOrMoreStepsMap.put(roleName, visitedRolesSet);

			logger.debug("buildRolesReachableInOneOrMoreStepsMap() - From role " + roleName
					+ " one can reach " + visitedRolesSet + " in one or more steps.");
		}

	}

}
