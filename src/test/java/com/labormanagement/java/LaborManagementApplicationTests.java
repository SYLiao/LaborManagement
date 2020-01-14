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
		Date date = new Date(1995, 8, 14);
		TimeSheet timeSheet = timeSheetRepository.findById(1L).orElse(null);
		User user = userRepository.findById(1L).orElse(null);
		List<TimeSheet> list = user.getSheets();
		list.add(timeSheet);
		user.setSheets(list);
		userRepository.save(user);
	}

}
