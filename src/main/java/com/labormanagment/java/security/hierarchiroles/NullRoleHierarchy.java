package com.labormanagment.java.security.hierarchiroles;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public final class NullRoleHierarchy implements RoleHierarchy {

	public Collection<? extends GrantedAuthority> getReachableGrantedAuthorities(
			Collection<? extends GrantedAuthority> authorities) {
		return authorities;
	}

}
