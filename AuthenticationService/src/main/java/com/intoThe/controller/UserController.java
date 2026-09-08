package com.intoThe.controller;

import com.intoThe.dto.UserDTO;
import com.intoThe.dto.request.PasswordResetRequest;
import com.intoThe.dto.request.UserRegistrationRequest;
import com.intoThe.dto.request.UserUpdateRequest;
import com.intoThe.dto.response.AuthenticationServiceResponse;
import com.intoThe.service.impl.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

@RestController
@RequestMapping(path = "/userService", produces = MediaType.APPLICATION_JSON_VALUE)
//@Tag(
//        name = "User Management APIs",
//        description = "APIs for managing users"
//)
@Tag(name = "User Management APIs")
public class UserController {
    //@Autowired
    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    /**
     * This endpoint is used to create a new user in the system.
     *
     * @param registrationRequest The user data to be created.
     * @return A string indicating that the user was successfully created.
     */
    @Operation(
            summary = "Register a new user",
            description = "Registers a new user using the provided username, email address, and password. " +
                    "Upon successful registration, a verification link is sent to the user's email address."
    )
    @PostMapping("/createNewUser")
    public ResponseEntity<?> createNewUser(@Valid  @RequestBody UserRegistrationRequest registrationRequest){
        return userService.addUser(registrationRequest);
    }


    /**
     * This endpoint is used to update an existing user in the system.
     *
     * @param updateRequest The updated user data.
     * @return The updated user data wrapped in a {@link ResponseEntity} object
     *         with an HTTP status of {@link HttpStatus#ACCEPTED}.
     */
    @Operation(
            summary = "Update user details",
            description = "Updates the details of an existing user using the information provided in the request.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "User details updated successfully."
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid user details provided."
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "User not found."
            )
    })
    @PutMapping("/updateUser")
    public ResponseEntity<String> updateUser(@RequestBody UserUpdateRequest updateRequest){
        return new ResponseEntity<>(userService.updateUser(updateRequest), HttpStatus.ACCEPTED);
    }

    /**
     * This endpoint is used to retrieve the user information for a specified user ID.
     *
     * @param userName The ID of the user to retrieve information for.
     * @return The user information wrapped in a {@link ResponseEntity} object, with an HTTP status of {@link HttpStatus#FOUND}.
     */
    @Operation(
            summary = "Get user details",
            description = "Retrieves the details of a user using the specified username."
    )
    @Parameter(
            name = "userName",
            description = "Username of the user whose details are to be retrieved.",
            required = true,
            example = "AmanPandey1216"
    )
    @GetMapping("/getUserInfoById")
    public ResponseEntity<UserDTO> getUserById(@RequestHeader String userName){
        return new ResponseEntity<>(userService.getUserInfo(userName),HttpStatus.FOUND);
    }

    /**
     * This endpoint is used to retrieve information for all users in the system.
     *
     * @return A list of user information wrapped in a {@link ResponseEntity} object,
     *         with an HTTP status of {@link HttpStatus#FOUND}.
     */
    @Operation(
            summary = "Get all users",
            description = "Retrieves the details of all users."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Successfully retrieved all users."
    )
    @GetMapping("/getAllUsers")
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(),HttpStatus.FOUND);
    }

    /**
     * This endpoint is used to activate or deactivate a user in the system.
     *
     * @param userName The ID of the user to be activated or deactivated.
     * @param isActive A string that is case-insensitive and can only be true for activation or false for deactivation.
     * @return A {@link ResponseEntity} object containing a string indicating that the user has been successfully activated or deactivated,
     *         with an HTTP status of {@link HttpStatus#ACCEPTED}.
     * @throws IllegalArgumentException if the isActive parameter is neither true nor false.
     */

    @Operation(
            summary = "Activate or deactivate user",
            description = "Updates the active status of the specified user. Set the active status to true to activate the user or false to deactivate the user."
    )
    @Parameters({
            @Parameter(
                    name = "userName",
                    description = "Username of the user whose account status is to be updated.",
                    required = true,
                    example = "AmanPandey1216"
            ),
            @Parameter(
                    name = "isActive",
                    description = "Indicates whether the user account should be active. Set to true to activate the user or false to deactivate the user.",
                    required = true,
                    example = "true"
            )
    })
    @PatchMapping("/activateOrDeactivateUser")
    public ResponseEntity<AuthenticationServiceResponse> modifyUserStatus(@RequestHeader String userName,
                                                   @RequestHeader Boolean isActive){
        return userService.activateOrDeactivate(userName, isActive);
    }

    @Operation(
            summary = "Delete user",
            description = "Deletes the user account associated with the specified username."
    )
    @Parameter(
            name = "userName",
            description = "Username of the user account to be deleted.",
            required = true,
            example = "AmanPandey1216"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "User account deleted successfully."
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "User not found."
            )
    })
    @DeleteMapping("/deleteUser")
    public ResponseEntity<AuthenticationServiceResponse> deleteUser(@Valid @RequestHeader String userName){
        return userService.deleteUser(userName);
    }

    @Operation(
            summary = "Request password reset",
            description = "Initiates the password reset process for the specified email address. " +
                    "If the email address is associated with a registered user, a password reset link " +
                    "containing a secure token is sent to that email address."
    )
    @Parameter(
            name = "userEmail",
            description = "Email address associated with the user account.",
            required = true,
            example = "user@example.com"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Password reset request processed successfully. If the email address is registered, " +
                    "a password reset link has been sent. Please check your email."
    )
    @PostMapping("/password-reset-request")
    public ResponseEntity<AuthenticationServiceResponse> passwordResetRequest(
            @NotBlank(message = "Email Id can't be empty")
            @Email(message = "Email Id should be in proper format")
            @RequestParam String userEmail){

        return userService.passwordResetRequest(userEmail);
    }

    @Operation(
            summary = "Reset user password",
            description = "Resets the user's password using the password reset token received through the password reset link. " +
                    "The current password and new password must be provided. " +
                    "If the token and current password are valid, the user's password is updated successfully. " +
                    "If the token is invalid or expired, the password is not changed."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Password reset successfully."
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid or expired password reset token, or invalid password details."
            )
    })
    @PostMapping("/password-reset")
    public ResponseEntity<AuthenticationServiceResponse> passwordReset(
            @Valid @RequestBody PasswordResetRequest passwordResetRequest) {
        return userService.passwordReset(passwordResetRequest);
    }

    @Operation(
            summary = "Request password reset",
            description = "Initiates the password reset process for the specified email address. " +
                    "If the email address is associated with a registered user, a password reset link " +
                    "containing a secure token is sent to the user's email address."
    )
    @Parameter(
            name = "userEmailId",
            description = "Email address associated with the user account.",
            required = true,
            example = "user@example.com"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Password reset request processed successfully. If the email address is registered, " +
                    "a password reset link has been sent. Please check your email."
    )
    @PostMapping("/forgot-password-request")
    public ResponseEntity<AuthenticationServiceResponse> forgotPasswordRequest(
            @NotBlank(message = "User Email Id can't be blank") @RequestParam String userEmailId
    ){
        return userService.forgotPasswordRequest(userEmailId);
    }

    @Operation(
            summary = "Reset user password",
            description = "Resets the user's password using the password reset token received through the password reset link. " +
                    "If the token is valid and has not expired, the user's password is updated successfully."
    )
    @Parameters({
            @Parameter(
                    name = "token",
                    description = "Password reset token received through the password reset link.",
                    required = true,
                    example = "eyJhbGciOiJIUzI1NiJ9..."
            ),
            @Parameter(
                    name = "newPassword",
                    description = "New password to be set for the user account.",
                    required = true,
                    example = "NewStrongPassword@123",
                    schema = @Schema(type = "string", format = "password")
            )
    })
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Password reset successfully."
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid or expired password reset token."
            )
    })
    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(
            @RequestHeader String token,
            @RequestHeader String newPassword
    ){
        return userService.forgetPassword(token, newPassword);
    }

}
