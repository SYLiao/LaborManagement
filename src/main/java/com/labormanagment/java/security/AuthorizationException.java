package com.labormanagment.java.security;

public class AuthorizationException {

	public class AuthorizationServiceException extends AccessDeniedException {
		// ~ Constructors
		// ===================================================================================================

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
			super(msg, t);
		}
	}
}
