package com.labormanagement.java.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class JobManager {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long jobId;
	
	private String jobCode;
	
	private String description;
	
	private double rate;
	
	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	private double amout;
	
	@OneToOne
	private JobWorkload jobWorkload;

	public JobManager() {
		
	}
	
	public JobManager(String jobCode, String description, double amount) {
		this.jobCode = jobCode;
		this.amout = amount;
		this.description  =description;
	}

	public long getJobId() {
		return jobId;
	}

	public void setJobId(long jobId) {
		this.jobId = jobId;
	}

	public String getJobCode() {
		return jobCode;
	}

	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getAmout() {
		return amout;
	}

	public void setAmout(double amout) {
		this.amout = amout;
	}

	public JobWorkload getWorkloads() {
		return jobWorkload;
	}

	public void setWorkloads(JobWorkload workloads) {
		this.jobWorkload = workloads;
	}
}
