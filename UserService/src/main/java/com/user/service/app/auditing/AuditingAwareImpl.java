package com.user.service.app.auditing;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("auditAwareClass")
public class AuditingAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        if (authentication == null ||
                authentication instanceof AnonymousAuthenticationToken ||
                !authentication.isAuthenticated()) {

            return Optional.of("anonymous");
        }

        return Optional.ofNullable(authentication.getName())
                .filter(name -> !name.isBlank())
                .or(() -> Optional.of("anonymous"));
    }
}
