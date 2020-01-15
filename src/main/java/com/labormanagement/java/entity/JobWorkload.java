package com.labormanagement.java.entity;

import javax.persistence.*;

@Entity
public class JobWorkload {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long jobworkId;
	
	private double rate;
	
	private double hours;
	
	private double amount;
	
	@ManyToOne
	private JobManager jobManager;
	
	public JobWorkload() {
		
	}
	
	public JobWorkload(double rate, double hours, double amount) {
		this.amount = amount;
		this.hours = hours;
		this.rate = rate;
	}

	public long getJobworkId() {
		return jobworkId;
	}

	public void setJobworkId(long jobworkId) {
		this.jobworkId = jobworkId;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public double getHours() {
		return hours;
	}

	public void setHours(double hours) {
		this.hours = hours;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public JobManager getJobManager() {
		return jobManager;
	}

	public void setJobManager(JobManager jobManager) {
		this.jobManager = jobManager;
	}
	
	
}
