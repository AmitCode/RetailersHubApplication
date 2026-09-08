package com.intoThe.controller;

import com.intoThe.service.impl.TokenVerificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/verify", produces = MediaType.APPLICATION_JSON_VALUE)
//@Tag(
//        name = "Token Management APIs",
//        description = "APIs for managing tokens"
//)
//Instead of using it in the controllers we can manage them from global openapi configuration class.
@Tag(name = "Token Management APIs")
public class TokenController {

    private final TokenVerificationService verificationService;
    public TokenController(TokenVerificationService verificationService){
        this.verificationService = verificationService;
    }

    @Operation(
            summary = "Verify user account",
            description = "Verifies a user's account using the verification token received in the email verification link after successful registration."
    )
    @Parameter(
            name = "verificationToken",
            description = "Verification token included in the email verification link.",
            required = true
    )
    @GetMapping("/verifyUserAccount")
    public ResponseEntity<?> verifyUser(@RequestParam String verificationToken) throws Exception {
        return new ResponseEntity<>(verificationService.verifyToken(verificationToken), HttpStatus.OK);
    }

    @Operation(
            summary = "Regenerate user verification token",
            description = "Generates a new verification token for the specified user's email address."
    )
    @Parameter(
            name = "userEmail",
            description = "Email address of the user for whom a new verification token is requested.",
            required = true,
            example = "user@example.com"
    )
    @PostMapping("/regenerateToken")
    private ResponseEntity<?> regenerateToken(@RequestHeader String userEmail){
        return verificationService.regenerateVerificationLink(userEmail);
    }
}
