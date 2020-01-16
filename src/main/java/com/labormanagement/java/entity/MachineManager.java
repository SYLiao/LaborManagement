package com.labormanagement.java.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.mapping.Array;

@Entity
public class MachineManager {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long MachineId;
	
	private String machineCode;
	
	private String description;
	
	private double rate;
	
	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	private double amount;
	
	@OneToOne
	private MachineWorkload workload;
	
	public long getMachineId() {
		return MachineId;
	}

	public void setMachineId(long machineId) {
		MachineId = machineId;
	}

	public String getMachineCode() {
		return machineCode;
	}

	public void setMachineCode(String machineCode) {
		this.machineCode = machineCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public MachineWorkload getWorkloads() {
		return workload;
	}

	public void setWorkloads(MachineWorkload workloads) {
		this.workload = workloads;
	}


	public MachineManager() {
		
	}
	
	public MachineManager(String machineCode, String description) {
		this.description = description;
		this.machineCode = machineCode;
	}
}
