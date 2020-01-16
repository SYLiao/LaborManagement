package com.labormanagement.java.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.labormanagement.java.entity.*;
import com.labormanagement.java.reposit.AuthorityRepository;
import com.labormanagement.java.reposit.UserRepository;

@Service
public class MyUserDetailService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AuthorityRepository authorityRepository;
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		Iterable<User> usersIterable = userRepository.findAll();
		UserDetails userDetails = null;
		for (User user : usersIterable) {
			if(user.getUsername().equals(username)) {
				userDetails = user;
			}
		}
		return userDetails;
	}
	
	public Authority findByName(String roleName) {
		Iterable<Authority> iterable = authorityRepository.findAll();
		for(Authority authority : iterable) {
			if(authority.getAuthority().equals(roleName)) {
				return authority;
			}
		}
		return null;
	}
}
