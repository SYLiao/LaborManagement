package com.labormanagment.java.security;

import org.springframework.security.access.intercept.RunAsUserToken;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.SpringSecurityMessageSource;

import org.springframework.beans.factory.InitializingBean;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.support.MessageSourceAccessor;

import org.springframework.util.Assert;

public class RunAsImplAuthenticationProvider implements InitializingBean,
		AuthenticationProvider, MessageSourceAware {
	// ~ Instance fields
	// ================================================================================================

	protected MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();
	private String key;

	// ~ Methods
	// ========================================================================================================

	public void afterPropertiesSet() {
		Assert.notNull(key,
				"A Key is required and should match that configured for the RunAsManagerImpl");
	}

	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		RunAsUserToken token = (RunAsUserToken) authentication;

		if (token.getKeyHash() == key.hashCode()) {
			return authentication;
		}
		else {
			throw new BadCredentialsException(messages.getMessage(
					"RunAsImplAuthenticationProvider.incorrectKey",
					"The presented RunAsUserToken does not contain the expected key"));
		}
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public void setMessageSource(MessageSource messageSource) {
		this.messages = new MessageSourceAccessor(messageSource);
	}

	public boolean supports(Class<?> authentication) {
		return RunAsUserToken.class.isAssignableFrom(authentication);
	}

}
