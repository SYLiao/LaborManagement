package com.labormanagment.java.security;

public class AfterInvocationProvider {
	
		// ~ Methods
		// ========================================================================================================

		Object decide(Authentication authentication, Object object,
				Collection<ConfigAttribute> attributes, Object returnedObject)
				throws AccessDeniedException;

		
		
		boolean supports(ConfigAttribute attribute);

	
		boolean supports(Class<?> clazz);
	}
}
