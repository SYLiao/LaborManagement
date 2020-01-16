package com.labormanagment.java.security;

import java.util.Collection;

import org.springframework.security.access.ConfigAttribute;

public interface Security {

	
	// ~ Methods
	// ========================================================================================================

	
	Collection<ConfigAttribute> getAttributes(Object object)
			throws IllegalArgumentException;


	Collection<ConfigAttribute> getAllConfigAttributes();
	
	boolean supports(Class<?> clazz);
}

