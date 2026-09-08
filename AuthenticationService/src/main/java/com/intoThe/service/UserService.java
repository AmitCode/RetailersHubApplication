package com.intoThe.service;

import com.intoThe.dto.UserDTO;
import com.intoThe.dto.request.PasswordResetRequest;
import com.intoThe.dto.request.UserRegistrationRequest;
import com.intoThe.dto.request.UserUpdateRequest;
import com.intoThe.dto.response.AuthenticationServiceResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    public ResponseEntity<?> addUser(UserRegistrationRequest registrationRequest);
    public String updateUser(UserUpdateRequest updateRequest);
    public ResponseEntity<AuthenticationServiceResponse> deleteUser(String userName);
    public ResponseEntity<AuthenticationServiceResponse> activateOrDeactivate(String userName, Boolean isActive);
    public UserDTO getUserInfo(String userName);
    public List<UserDTO> getAllUsers();
    public ResponseEntity<AuthenticationServiceResponse> passwordResetRequest(String userEmail);
    public ResponseEntity<AuthenticationServiceResponse> passwordReset(PasswordResetRequest passwordResetRequest);
    public ResponseEntity<AuthenticationServiceResponse> forgotPasswordRequest(String userEmailId);
    public ResponseEntity<?> forgetPassword(String token, String newPassword);
}
