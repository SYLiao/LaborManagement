package com.labormanagement.java.entity;

import javax.persistence.*;

@Entity
public class MachineWorkload {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long machineId;
	
	private double rate;
	
	private double hours;
	
	private double amount;
	
	@ManyToOne
	private MachineManager machineManager;
	
	public MachineWorkload() {
		
	}
	
	public MachineWorkload(double rate, double hours) {
		this.hours = hours;
		this.rate  =rate;
	}

	public long getMachineId() {
		return machineId;
	}

	public void setMachineId(long machineId) {
		this.machineId = machineId;
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

	public MachineManager getMachineManager() {
		return machineManager;
	}

	public void setMachineManager(MachineManager machineManager) {
		this.machineManager = machineManager;
	}
}
