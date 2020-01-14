package com.labormanagement.java.entity;

import javax.persistence.*;
import java.util.*;

@Entity
public class TimeSheet {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long TimeSheetId;
	
	private Date creatDate;
	
	@ManyToOne
	private User user;
	
	@ManyToMany
	private List<JobManager> jobManagers;
	
	@ManyToMany
	private List<MachineManager> machineManagers;
	
	private String siteCode;
	
	private double amount;
	
	private String status;
	
	public TimeSheet(){
		this.status = "Submitted";
	}
	
	public TimeSheet(Date date, String sitecode) {
		this.creatDate = date;
		this.siteCode = sitecode;
		this.status = "Submitted";
	}

	public long getTimeSheetId() {
		return TimeSheetId;
	}

	public void setTimeSheetId(long timeSheetId) {
		TimeSheetId = timeSheetId;
	}

	public Date getCreatDate() {
		return creatDate;
	}

	public void setCreatDate(Date creatDate) {
		this.creatDate = creatDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<JobManager> getJobManagers() {
		return jobManagers;
	}

	public void setJobManagers(List<JobManager> jobManagers) {
		this.jobManagers = jobManagers;
	}

	public List<MachineManager> getMachineManagers() {
		return machineManagers;
	}

	public void setMachineManagers(List<MachineManager> machineManagers) {
		this.machineManagers = machineManagers;
	}

	public String getSiteCode() {
		return siteCode;
	}

	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
