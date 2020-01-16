package com.labormanagement.java;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.labormanagement.java.entity.Role;
import com.labormanagement.java.entity.TimeSheet;
import com.labormanagement.java.entity.User;
import com.labormanagement.java.reposit.RoleRepository;
import com.labormanagement.java.reposit.TimeSheetRepository;
import com.labormanagement.java.reposit.UserRepository;

@SpringBootTest
class LaborManagementApplicationTests {
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	TimeSheetRepository timeSheetRepository;
	
	@Autowired
	UserRepository userRepository;

	@Test
	void contextLoads() {
		Role admin= new Role("admin");
		roleRepository.save(admin);
		User user = new User("1234", "1234", admin);
		userRepository.save(user);
	}

}
