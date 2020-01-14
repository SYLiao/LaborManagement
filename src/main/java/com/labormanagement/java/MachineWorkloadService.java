package com.labormanagement.java.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.event.PublicInvocationEvent;
import org.springframework.stereotype.Service;
import com.labormanagement.java.entity.MachineWorkload;
import com.labormanagement.java.entity.User;
import com.labormanagement.reposit.MachineWorkloadRepository;
import com.labormanagement.reposit.UserRepository;


@Service
public class MachineWorkloadService {
	
	public MachineWorkload findById(long machineID) {

		MachineWorkload MachineID = MachineWorkloadRepository.findById(MachineID).orElse(null);

		return MachineWorkload;
		
	

		
		

	}
	
	
}
