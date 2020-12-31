package com.monarch.UM.audit;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

public class AuditorAwareImpl implements AuditorAware<String> {
	@Override
	public Optional<String> getCurrentAuditor() {
		String authName = (String) RequestContextHolder.currentRequestAttributes().getAttribute("username",
				RequestAttributes.SCOPE_REQUEST);
		return Optional.of(authName);
	}
}
