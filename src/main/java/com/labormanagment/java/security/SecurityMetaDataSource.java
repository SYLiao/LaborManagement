package com.labormanagment.java.security;


	import java.util.Collection;

import org.springframework.aop.framework.AopInfrastructureBean;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;


Collection<ConfigAttribute> getAttributes(Object object)
		throws IllegalArgumentException;


Collection<ConfigAttribute> getAllConfigAttributes();

boolean supports(Class<?> clazz);
}
