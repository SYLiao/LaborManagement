package com.labormanagement.java.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labormanagement.java.entity.Role;
import com.labormanagement.java.entity.User;
import com.labormanagement.reposit.RoleRepository;


	@Service
	public class RoleService {

		@Autowired
		private RoleRepository RoleRepository;

		public Role findById(long id) {

			Role role = RoleRepository.findById(id).orElse(null);

			return role;
		}
	
		
		public List<Role> findAll(){

			return (List<Role>) RoleRepository.findAll();

		}

		

		public void createRole(Role role) {

			RoleRepository.save(role);

		}

		

		public void deleteRoleById(long id) {

			Role role = findById(id);

			if(role == null) {

				return;

			}

			RoleRepository.delete(role);

		}

		

		public void deleteAll() {

			RoleRepository.deleteAll();

		}

		

		public void updateRole(Role role) {

			Role role2 = RoleRepository.findById(role.getRoleId()).orElse(null);

			if(role2 != null) {

				RoleRepository.save(role);

			}

		}

	
}
