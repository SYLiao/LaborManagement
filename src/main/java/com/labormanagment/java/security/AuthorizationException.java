package com.labormanagment.java.security;

import java.nio.file.AccessDeniedException;

public class AuthorizationException {

	public class AuthorizationServiceException extends AccessDeniedException {
		// ~ Constructors
		// ===================================================================================================

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * Constructs an <code>AuthorizationServiceException</code> with the specified
		 * message.
		 */
		public AuthorizationServiceException(String msg) {
			super(msg);
		}

		/**
		 * Constructs an <code>AuthorizationServiceException</code> with the specified message
		 * and root cause.
		 */
		public AuthorizationServiceException(String msg, Throwable t) {
			super(msg);
		}
	}
}
