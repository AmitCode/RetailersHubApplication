package com.user.service.app.controllers;

import com.user.service.app.dtos.UserDto;
import com.user.service.app.dtos.request.UserCreationRequest;
import com.user.service.app.dtos.response.UserServiceOprResponse;
import com.user.service.app.services.impl.UserServicesImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/usersOpr")

@Tag(name = "User Management APIs")
public class UserController {
    private final UserServicesImpl userServices;
    UserController(UserServicesImpl userServices){
        this.userServices = userServices;
    }

    @Operation(
            summary = "Get all users",
            description = "Retrieves a list of all users registered in the User Service."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Users retrieved successfully",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(
                                    schema = @Schema(implementation = UserDto.class)
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "No users found",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content
            )
    })
    @GetMapping("/getAllUsers")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return new ResponseEntity<>(userServices.getAllUsers(), HttpStatus.OK);
    }


    @Operation(
            summary = "Create a new user",
            description = """
                Creates a new user in the User Service after successful user registration
                and verification by the Auth Service.
                
                This API is intended for internal service-to-service communication.
                The request contains the user information required to create the user account.
                """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "User created successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserServiceOprResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid user creation request",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "User already exists",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content
            )
    })
    @PostMapping("/v1/addNewUser")
    public ResponseEntity<UserServiceOprResponse> v1AddNewUser(
            @Valid @RequestBody UserCreationRequest request){
        return new ResponseEntity<>(userServices.addNewUserV1(request), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Create a new user",
            description = """
                Creates a new user in the User Service using the provided user details.
                
                This API is intended for internal service-to-service communication.
                It is invoked after successful user registration and verification by
                the Auth Service.
                
                The user information is validated before being persisted. If a user
                with the same unique username already exists, the request is rejected.
                """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "User created successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = UserServiceOprResponse.class
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid user details provided",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "User already exists",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content
            )
    })
    @PostMapping("/v2/addNewUser")
    public ResponseEntity<UserServiceOprResponse> v2AddNewUser(@Valid @RequestBody UserDto userDto){
        return new ResponseEntity<>(userServices.addNewUserV2(userDto), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Update user details",
            description = """
                Updates the details of an existing user in the User Service.
                
                The request contains the updated user information and is validated
                before being passed to the user service layer for processing.
                """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "202",
                    description = "User update request accepted successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = UserServiceOprResponse.class
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid user details provided",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "User not found",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "User details conflict with existing data",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content
            )
    })
    @PutMapping("/updateUserDetails")
    public ResponseEntity<UserServiceOprResponse> updateUserDetails(@Valid @RequestBody UserDto userDto){
        return new ResponseEntity<>(userServices.updateUserDetails(userDto), HttpStatus.ACCEPTED);
    }

    @Operation(
            summary = "Delete user",
            description = """
                Deletes an existing user from the User Service using the username
                provided in the request header.
                
                The user is permanently removed if the specified username exists.
                """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "User deleted successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = UserServiceOprResponse.class
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid or missing username",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "User not found",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content
            )
    })
    @DeleteMapping("/deleteUser")
    public ResponseEntity<UserServiceOprResponse> deleteUser(@Valid @RequestHeader String userName){
        return userServices.deleteUserByUserName(userName);
    }

    @Operation(
            summary = "Activate or deactivate user",
            description = """
                Activates or deactivates an existing user account based on the
                username and the requested active status.
                
                Set isActive to true to activate the user account or false to
                deactivate the user account.
                """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "User status updated successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = UserServiceOprResponse.class
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid username or active status",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "User not found",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content
            )
    })
    @PatchMapping("/activateOrDeactivateUser")
    public ResponseEntity<UserServiceOprResponse> modifyUserStatus(@RequestHeader String userName,
                                                                          @RequestHeader Boolean isActive){
        return userServices.activateOrDeactivate(userName, isActive);
    }

    @Operation(
            summary = "Update user profile",
            description = """
                Updates the profile details of an existing user identified by the
                user ID.
                
                The supplied user profile data is validated before the update is
                processed.
                """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "User profile updated successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = UserServiceOprResponse.class
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid user profile data or user ID",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "User not found",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "User profile conflicts with existing data",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content
            )
    })
    @PutMapping("/{userId}/profile")
    public ResponseEntity<UserServiceOprResponse> updateUserProfile(@PathVariable Long userId,
                                                                    @Valid @RequestBody UserDto userDto){
        return userServices.updateUserProfile(userId, userDto);
    }

}
