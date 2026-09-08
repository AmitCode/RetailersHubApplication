package com.intoThe.controller;

import com.intoThe.dto.response.OtpServiceResponse;
import com.intoThe.repository.OtpRepository;
import com.intoThe.service.OtpService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@Tag(
//        name = "Otp Management APIs",
//        description = "APIs for managing otp")

@Tag(
        name = "Otp Management APIs"
)
@RequestMapping(path = "/otpService", produces = MediaType.APPLICATION_JSON_VALUE)
public class OtpController {

    private final OtpService otpService;
    public OtpController(OtpService otpService){
        this.otpService = otpService;
    }

    @Operation(
            summary = "Generate OTP using email address",
            description = "Generates a one-time password (OTP) for the provided email address. " +
                    "If the email address is registered and valid, the generated OTP is sent to the specified " +
                    "email address."
    )
    @PostMapping("/generateOtp")
    public ResponseEntity<OtpServiceResponse> generateOtp(@RequestHeader String userEmail){
        return otpService.generateOtp(userEmail);
    }

    @Operation(
            summary = "Validate OTP",
            description = "Validates the OTP provided for the specified email address. " +
                    "The OTP is considered valid only if it matches the OTP generated " +
                    "for the provided email address and is still within its validity period."
    )
    @PostMapping("/validateOtp")
    public ResponseEntity<OtpServiceResponse> validateOtp(@Valid
            @NotBlank(message = "Please provide otp!") @RequestHeader String otp,
            @NotBlank(message = "Please enter a mail!") @Email(message = "Not an email!") @RequestHeader String email){
        return otpService.validateOtp(email, otp);
    }

}
