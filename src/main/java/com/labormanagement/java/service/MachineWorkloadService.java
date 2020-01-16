package com.labormanagement.java.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labormanagement.java.entity.MachineManager;
import com.labormanagement.java.entity.MachineWorkload;
import com.labormanagement.java.reposit.MachineWorkloadRepository;

@Service
public class MachineWorkloadService {

	@Autowired
	private MachineWorkloadRepository machineWorkloadRepository;
	
	public void createMachineWorkload(MachineWorkload machineWorkload) {
		machineWorkloadRepository.save(machineWorkload);
	}
	
	public MachineWorkload findById(long id) {
		MachineWorkload machineWorkload = machineWorkloadRepository.findById(id).orElse(null);
		return machineWorkload;
	}
	
	public List<MachineWorkload> findAll(){
		return (List<MachineWorkload>) machineWorkloadRepository.findAll();
	}
	
	public void updateMachineWorkload(MachineWorkload machineWorkload) {
		MachineWorkload machineWorkload2 = machineWorkloadRepository.findById(machineWorkload.getMachineId()).orElse(null);
		if(machineWorkload2 != null) {
			machineWorkloadRepository.save(machineWorkload);
		}
	}
	
	public void deleteById(long id) {
		machineWorkloadRepository.deleteById(id);
	}
	
	public void deleteAll() {
		machineWorkloadRepository.deleteAll();
	}
}
