package com.labormanagement.java.service;

import com.labormanagement.java.entity.Role;
import com.labormanagement.reposit.MachineManagerRepository;

public class MachineManagerService {
 public MachineManagerService() {
	 
	 
	 
	 public void createMachineManager(MachineManager machinemanager) {

			MachineManagerRepository.save(machinemanager);

			
			
	public void deleteMachineManagerById(long id) {

				Role machinemanageRole = MachineManager(id);

				if(machinemanageRole == null) {

					return;

				}

				MachineManagerRepository.delete(machinemanager);

			}
		}

}
}
