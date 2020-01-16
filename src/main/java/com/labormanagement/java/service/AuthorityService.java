package com.labormanagement.java.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labormanagement.java.entity.Authority;
import com.labormanagement.java.reposit.AuthorityRepository;

@Service
public class AuthorityService {
	
	@Autowired
	private AuthorityRepository authorityRepository;
	
	public Authority findByName(String roleName) {
		Iterable<Authority> iterable = authorityRepository.findAll();
		for(Authority authority : iterable) {
			if(authority.getAuthority().equals(roleName)) {
				return authority;
			}
		}
		return null;
	}

	
	public void createAuthority(Authority authority) {
		authorityRepository.save(authority);
	}
}
