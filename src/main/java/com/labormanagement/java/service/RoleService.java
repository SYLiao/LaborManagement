package com.labormanagement.java.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labormanagement.java.entity.Role;
import com.labormanagement.java.reposit.RoleRepository;

@Service
public class RoleService {
	
	@Autowired
	private RoleRepository roleRepository;
	
	public void createRole(Role role) {
		roleRepository.save(role);
	}
	
	public Role findById(long id) {
		Role role = roleRepository.findById(id).orElse(null);
		return role;
	}
	
	public Role findByName(String name){
		Iterable<Role> rolesIterable = roleRepository.findAll();
		for(Role role : rolesIterable) {
			if(role.getName().equals(name)) {
				return role;
			}
		}
		return null;
	}
	
	public List<Role> findAll(){
		return (List<Role>) roleRepository.findAll();
	}
	
	public void updateRole(Role role) {
		Role role2 = roleRepository.findById(role.getRoleId()).orElse(null);
		if(role2 != null) {
			roleRepository.save(role);
		}
	}
	
	public void deleteById(long roleId) {
		roleRepository.deleteById(roleId);
	}
	
	public void deleteAll() {
		roleRepository.deleteAll();
	}
}
