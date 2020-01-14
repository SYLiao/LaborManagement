package com.labormanagement.java.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labormanagement.java.entity.User;
import com.labormanagement.java.reposit.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public User findById(long id) {
		User user = userRepository.findById(id).orElse(null);
		return user;
	}
	
	public List<User> findByName(String name) {
		Iterable<User> users = userRepository.findAll();
		List<User> resultList = new ArrayList<>();
		for(User user: users) {
			if(user.getUsername().equals(name)) {
				resultList.add(user);
			}
		}
		return resultList;
	}
	
	public List<User> findAll(){
		return (List<User>) userRepository.findAll();
	}
	
	public void createUser(User user) {
		userRepository.save(user);
	}
	
	public void deleteUserById(long id) {
		userRepository.deleteById(id);
	}
	
	public void deleteAll() {
		userRepository.deleteAll();
	}
	
	public void updateUser(User user) {
		User user2 = userRepository.findById(user.getUserId()).orElse(null);
		if(user2 != null) {
			userRepository.save(user);
		}
	}
}
