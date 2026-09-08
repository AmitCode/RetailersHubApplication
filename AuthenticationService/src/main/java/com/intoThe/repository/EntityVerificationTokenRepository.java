package com.intoThe.repository;

import com.intoThe.entities.EntityVerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EntityVerificationTokenRepository extends JpaRepository<EntityVerificationToken, Long> {
    public Optional<EntityVerificationToken> findByVerificationToken(String token);
}
