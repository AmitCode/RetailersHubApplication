package com.intoThe.controller;

import com.intoThe.dto.request.UserLoginRequest;
import com.intoThe.dto.response.UserLoginResponse;
import com.intoThe.service.AuthService;
import com.intoThe.service.OtpService;
import com.intoThe.utils.JWTUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
//@Tag(
//        name = "Authentication Management APIs",
//        description = "APIs for managing user authentication"
//)
@Tag(
        name = "Authentication Management APIs"
)
public class RetailerAuthentication {

    //@Autowired
    //private final AuthService authService;
    private final AuthenticationManager authManager;
    private final JWTUtils jwtUtils;
    private final OtpService otpService;

    public RetailerAuthentication(AuthService authService, AuthenticationManager authManager,
                                  JWTUtils jwtUtils, OtpService otpService) {
        //this.authService = authService;
        this.authManager = authManager;
        this.jwtUtils = jwtUtils;
        this.otpService = otpService;
    }

    @Operation(
            summary = "Authenticate user",
            description = "Authenticates a user using their username and password and generates JWT access and " +
                    "refresh tokens upon successful authentication."
    )
    @PostMapping("/login")
    public ResponseEntity<UserLoginResponse> loginToSystem(@Valid @RequestBody UserLoginRequest
                                                                       userLoginRequest){
        UserLoginResponse response = new UserLoginResponse();

        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(userLoginRequest.getUserName(),
                        userLoginRequest.getPassword())
        );
        response.setStatusCode(String.valueOf(HttpStatus.OK));
        response.setIsLoginSuccess("Y");
        response.setLoginMessage(jwtUtils.generateJwtToken(userLoginRequest.getUserName()));

        return ResponseEntity.ok(response);
     }

    @Operation(
            summary = "Authenticate user using OTP",
            description = "Authenticates the user using the one-time password (OTP) sent to the email address or " +
                    "mobile number provided by the user, and returns a JWT upon successful authentication."
    )
    @Parameter(
            name = "otp",
            description = "One-time password (OTP) sent to the user's registered email address or mobile number.",
            required = true,
            example = "123456"
    )
    @PostMapping("/loginUsingOtp")
    public ResponseEntity<UserLoginResponse> loginUsingOtp(@RequestHeader String otp){

        UserLoginResponse response = new UserLoginResponse();
        String userName = otpService.validateOtpAuth(otp);

        response.setStatusCode(String.valueOf(HttpStatus.OK));
        response.setIsLoginSuccess("Y");
        response.setLoginMessage(jwtUtils.generateJwtToken(userName));
        return ResponseEntity.ok(response);
    }

}
