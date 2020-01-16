package com.labormanagement.java.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;

@Entity
public class Authority implements GrantedAuthority {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long AuthId;

	private String authority;
	
	public Authority(String authString) {
		// TODO Auto-generated constructor stub
		this.authority = authString;
	}

	public long getAuthId() {
		return AuthId;
	}

	public void setAuthId(long authId) {
		AuthId = authId;
	}
	
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return this.authority;
	}

}
