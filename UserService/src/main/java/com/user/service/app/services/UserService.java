package com.user.service.app.services;

import com.user.service.app.dtos.UserDto;
import com.user.service.app.dtos.request.UserCreationRequest;
import com.user.service.app.dtos.response.UserServiceOprResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    public List<UserDto> getAllUsers();
    public UserServiceOprResponse addNewUserV1(UserCreationRequest request);
    public UserServiceOprResponse addNewUserV2(UserDto userDto);
    public UserServiceOprResponse updateUserDetails(UserDto userDto);
    public ResponseEntity<UserServiceOprResponse> deleteUserByUserName(String userName);
    public ResponseEntity<UserServiceOprResponse> activateOrDeactivate(String userName, Boolean isActive);
    public ResponseEntity<UserServiceOprResponse> updateUserProfile(Long userId, UserDto userDetails);
}
