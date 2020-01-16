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
	
	@OneToOne
	private JobWorkload jobWorkload;
	
	@OneToOne
	private MachineWorkload machineWorkload;
	
	private String siteCode;
	
	private double Hours;

	private double amount;
	
	private String status;
	
	public TimeSheet(){
		this.status = "submitted";
	}
	
	public TimeSheet(Date date, String sitecode) {
		this.creatDate = date;
		this.siteCode = sitecode;
		this.status = "submitted";
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

	public JobWorkload getJobWorkload() {
		return jobWorkload;
	}

	public void setJobWorkload(JobWorkload jobWorkload) {
		this.jobWorkload = jobWorkload;
	}

	public MachineWorkload getMachineWorkload() {
		return machineWorkload;
	}

	public void setMachineWorkload(MachineWorkload machineWorkload) {
		this.machineWorkload = machineWorkload;
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
	
	public double getHours() {
		return Hours;
	}

	public void setHours(double hours) {
		Hours = hours;
	}

}
