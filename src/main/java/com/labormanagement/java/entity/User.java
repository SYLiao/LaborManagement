package com.labormanagement.java.entity;

import java.util.*;

import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class User implements UserDetails{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long userId;
	
	private String username;
	
	private String password;
	
	@ManyToOne
	private Role role;
	
	@OneToMany(fetch=FetchType.EAGER)
	private List<TimeSheet> sheets;
	
	public User() {
		this.sheets = new ArrayList<>();
	}
	
	public User(String username, String password) {
		this.username = username;
		this.password = password;
		this.sheets = new ArrayList<>();
	}
	public User(String username, String password, Role role) {
		this.username = username;
		this.password = password;
		this.role = role;
		this.sheets = new ArrayList<>();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		if(this.role != null) {
			return AuthorityUtils.commaSeparatedStringToAuthorityList(this.getRole().getName());
		}
		return null;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<TimeSheet> getSheets() {
		return sheets;
	}

	public void setSheets(List<TimeSheet> sheets) {
		this.sheets = sheets;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	
}
