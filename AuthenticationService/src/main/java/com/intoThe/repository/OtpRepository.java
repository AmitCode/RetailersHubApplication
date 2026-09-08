package com.intoThe.repository;

import com.intoThe.entities.OtpEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OtpRepository extends JpaRepository<OtpEntity, Long> {
    Optional<OtpEntity> findByOtp(String otp);
    Optional<OtpEntity> findByUserEmail(String email);
    Optional<OtpEntity> findByUserEmailAndIsValid(String otp, boolean isValid);
    Optional<OtpEntity> findByOtpAndIsValid(String otp, boolean isValid);
    Optional<OtpEntity> findByUserEmailAndIsValidAndIsVerified(String otp, boolean isValid, boolean isVerifies);
}
