package com.labormanagment.java.security;



	import java.util.Collection;

	import org.springframework.security.access.AccessDeniedException;
	import org.springframework.security.access.ConfigAttribute;
	import org.springframework.security.core.Authentication;


	public interface AfterInvocationManager {
		
		Object decide(Authentication authentication, Object object,
				Collection<ConfigAttribute> attributes, Object returnedObject)
				throws AccessDeniedException;

		
		boolean supports(ConfigAttribute attribute);


		boolean supports(Class<?> clazz);
	}
	
