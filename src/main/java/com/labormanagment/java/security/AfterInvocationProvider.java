package com.labormanagment.java.security;

import java.util.Collection;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;

public abstract class AfterInvocationProvider {
	
		// ~ Methods
		// ========================================================================================================

		abstract Object decide(Authentication authentication, Object object,
				Collection<ConfigAttribute> attributes, Object returnedObject)
				throws AccessDeniedException;

		
		abstract boolean supports(ConfigAttribute attribute);

	
		boolean supports(Class<?> clazz) {
			return false;
		}
	}

