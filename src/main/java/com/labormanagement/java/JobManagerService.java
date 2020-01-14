package com.labormanagement.java.service;

import org.springframework.data.annotation.CreatedBy;

import com.labormanagement.java.entity.JobManager;

public class JobManagerService {

	CreatedBy 
	updateBy
	Delete 
	Find
	Search
	
	
	  public JobManager createOrUpdateJobManager(JobManager entity) throws RecordNotFoundException 
    {
        Optional<JobManager> employee = repository.findById(entity.getId());
         
        if(employee.isPresent()) 
        {
            EmployeeEntity newEntity = employee.get();
            newEntity.setEmail(entity.getEmail());
            newEntity.setFirstName(entity.getFirstName());
            newEntity.setLastName(entity.getLastName());
 
            newEntity = repository.save(newEntity);
             
            return newEntity;
        } else {
            entity = repository.save(entity);
             
            return entity;
        }
    } 
	
	
	
}
