package com.labormanagement.java.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labormanagement.java.entity.JobManager;
import com.labormanagement.java.reposit.JobManagerRepository;

@Service
public class JobManagerService {

	@Autowired
	private JobManagerRepository jobManagerRepository;
	
	public void createJobManager(JobManager jobManager) {
		jobManagerRepository.save(jobManager);
	}
	
	public JobManager findById(long jobId) {
		JobManager jobManager = jobManagerRepository.findById(jobId).orElse(null);
		return jobManager;
	}
	
	public List<JobManager> findAll(){
		return (List<JobManager>) jobManagerRepository.findAll();
	}
	
	public JobManager findByJobCode(String jobCode){
		Iterable<JobManager> jobsIterable = jobManagerRepository.findAll();
		for(JobManager jobManager : jobsIterable) {
			if(jobManager.getJobCode().equals(jobCode)) {
				return jobManager;
			}
		}
		return null;
	}
	
	public void update(JobManager jobManager) {
		JobManager job = jobManagerRepository.findById(jobManager.getJobId()).orElse(null);
		if(job != null) {
			jobManagerRepository.save(jobManager);
		}
	}
	
	public void deleteById(long id) {
		jobManagerRepository.deleteById(id);
	}
	
	public void deleteAll() {
		jobManagerRepository.deleteAll();
	}
}
