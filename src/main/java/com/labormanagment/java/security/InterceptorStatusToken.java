package com.labormanagment.java.security;

	import java.util.Collection;
	import org.springframework.security.access.ConfigAttribute;
	import org.springframework.security.core.context.SecurityContext;

	public class InterceptorStatusToken {
		// ~ Instance fields
		// ================================================================================================

		private SecurityContext securityContext;
		private Collection<ConfigAttribute> attr;
		private Object secureObject;
		private boolean contextHolderRefreshRequired;

		// ~ Constructors
		// ===================================================================================================

		public InterceptorStatusToken(SecurityContext securityContext,
				boolean contextHolderRefreshRequired, Collection<ConfigAttribute> attributes,
				Object secureObject) {
			this.securityContext = securityContext;
			this.contextHolderRefreshRequired = contextHolderRefreshRequired;
			this.attr = attributes;
			this.secureObject = secureObject;
		}

		// ~ Methods
		// ========================================================================================================

		public Collection<ConfigAttribute> getAttributes() {
			return attr;
		}

		public SecurityContext getSecurityContext() {
			return securityContext;
		}

		public Object getSecureObject() {
			return secureObject;
		}

		public boolean isContextHolderRefreshRequired() {
			return contextHolderRefreshRequired;
		}
	}
	
