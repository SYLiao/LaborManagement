package com.labormanagement.java.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labormanagement.java.entity.MachineManager;
import com.labormanagement.java.reposit.MachineManagerRepository;

@Service
public class MachineManagerService {
	
	@Autowired
	MachineManagerRepository machineManagerRepository;
	
	public void createMachineManager(MachineManager machienManager) {
		machineManagerRepository.save(machienManager);
	}
	
	public MachineManager findById(long id) {
		MachineManager machineManager = machineManagerRepository.findById(id).orElse(null);
		return machineManager;
	}
	
	public List<MachineManager> findAll(){
		return (List<MachineManager>) machineManagerRepository.findAll();
	}
	
	public void updateMachineManager(MachineManager machineManager) {
		MachineManager machineManager2 = machineManagerRepository.findById(machineManager.getMachineId()).orElse(null);
		if(machineManager2 != null) {
			machineManagerRepository.save(machineManager2);
		}
	}
	
	public void deleteById(long id) {
		machineManagerRepository.deleteById(id);
	}
	
	public void deleteAll() {
		machineManagerRepository.deleteAll();
	}
}
