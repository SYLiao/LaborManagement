package com.labormanagement.java.entity;

import javax.persistence.*;

import java.text.SimpleDateFormat;
import java.util.*;

@Entity
public class TimeSheet {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long TimeSheetId;
	
	private String creatDate;
	
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
	
	private String user_name;
	
	public TimeSheet(){
		this.status = "submitted";
	}
	
	public TimeSheet(Date date, String sitecode) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		this.creatDate = format.format(date);
		this.siteCode = sitecode;
		this.status = "submitted";
	}

	public long getTimeSheetId() {
		return TimeSheetId;
	}

	public void setTimeSheetId(long timeSheetId) {
		TimeSheetId = timeSheetId;
	}

	public String getCreatDate() {
		return creatDate;
	}

	public void setCreatDate(Date creatDate) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		this.creatDate = format.format(creatDate);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
		setUser_name();
	}

	public JobWorkload getJobWorkload() {
		return jobWorkload;
	}

	public void setJobWorkload(JobWorkload jobWorkload) {
		this.jobWorkload = jobWorkload;
		this.Hours += this.jobWorkload.getHours();
		this.amount += this.jobWorkload.getJobManager().getRate()*this.jobWorkload.getHours();;
	}

	public MachineWorkload getMachineWorkload() {
		return machineWorkload;
	}

	public void setMachineWorkload(MachineWorkload machineWorkload) {
		this.machineWorkload = machineWorkload;
		this.Hours += this.machineWorkload.getHours();
		this.amount += this.machineWorkload.getMachineManager().getRate()*this.machineWorkload.getHours();
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
	
	public String getUser_name() {
		return user_name;
	}

	public void setUser_name() {
		if(this.user != null) {
			this.user_name = user.getUsername();
		}
		else{
			this.user_name = "None";
		}
	}
	
	public double getHours() {
		return Hours;
	}

	public void setHours(double hours) {
		Hours = hours;
	}

}
