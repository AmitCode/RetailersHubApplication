package com.intoThe.service;

import com.intoThe.dto.response.OtpServiceResponse;
import org.springframework.http.ResponseEntity;

public interface OtpService {
    public ResponseEntity<OtpServiceResponse> validateOtp(String email, String otp);
    public ResponseEntity<OtpServiceResponse> generateOtp(String email);
    public String validateOtpAuth(String otp);
}
