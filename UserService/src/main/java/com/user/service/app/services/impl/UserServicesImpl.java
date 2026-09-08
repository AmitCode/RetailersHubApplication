package com.user.service.app.services.impl;

import com.user.service.app.dtos.UserDto;
import com.user.service.app.dtos.request.UserCreationRequest;
import com.user.service.app.dtos.response.UserServiceOprResponse;
import com.user.service.app.entities.User;
import com.user.service.app.exceptions.SuppliersOprException.EmailIdAlreadyExist;
import com.user.service.app.exceptions.SuppliersOprException.ResourceNotFound;
import com.user.service.app.exceptions.SuppliersOprException.UserNameAlreadyExist;
import com.user.service.app.exceptions.SuppliersOprException.UserServiceException;
import com.user.service.app.mapper.UserModelMapper;
import com.user.service.app.repositories.UserRepository;
import com.user.service.app.services.UserService;
import com.user.service.app.utils.UserServiceUtils;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServicesImpl implements UserService {
    private final UserRepository userRepository;
    private final WebClient webClient;
    private UserServiceOprResponse response;
    UserServicesImpl(UserRepository userRepository, WebClient webClient){
        this.userRepository = userRepository;
        this.webClient = webClient;
    }

    public List<UserDto> getAllUsers(){
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = new ArrayList<>();
        for(User user : users){
            userDtos.add(UserModelMapper.mapToUserDTO(user));
        }
        return userDtos;
    }

    @Override
    @Transactional
    public UserServiceOprResponse addNewUserV1(UserCreationRequest request){
        try{
            if(UserServiceUtils.isEmailExist(request.getUserEmail(), userRepository))
                throw new EmailIdAlreadyExist("User already exist with email id!...");
            User user = UserModelMapper.mapToUserV1(request);
            User newUser = userRepository.save(user);
//            EmailRequest emailRequest = new EmailRequest(request.getUserName(),
//                    request.getUserEmail(),
//                    "Registration",
//                    "User Registration Conformation",
//                    "http://localhost:8088/userService/approve");
//            ResponseEntity<EmailServiceResponse> emailResponse = WebClientService.callNotificationServiceEmail(
//                    emailRequest, webClient
//            );
//            if(emailResponse.getStatusCode().is2xxSuccessful()){
//
//            } ---> Commented this line of code because if email service is failed in that case the
            // user creation process will not be considered as failed instead the user will be allowed
            // to send re-verification code.

//            if(null == emailServiceResponse || null == emailServiceResponse.getEmailStatus() ||
//                    emailServiceResponse.getEmailStatus().equalsIgnoreCase("true")){
//
//                throw new InvalidStatusException((emailServiceResponse.getEmailMessage() == null) ?
//                "Something went wrong!..": emailServiceResponse.getEmailMessage());
//            }
            response = UserServiceOprResponse.createResponse()
                    .setStatusCode(HttpStatus.CREATED.toString())
                    .setIsOprSuccess(true)
                    .setResponseMsg("User has been added successfully!.");
        }catch (DataIntegrityViolationException exception){
            throw new UserNameAlreadyExist("User already exists!...");
        }

//        Optional<User> userOptional = repository.findByUserName(request.getUserName());
//        if(userOptional.isPresent())
//            throw new UserNameAlreadyExist("User already exists!...");
//        User user = UserModelMapper.mapToUserV1(request);
//        User newUser = repository.save(user);
//        response.setStatusCode(HttpStatus.CREATED.toString())
//                .setIsOprSuccess(true)
//                .setResponseMsg("User has been added successfully with id : "+ user.getUserId() +"!...");

        return response;
    }

    @Override
    @Transactional
    public UserServiceOprResponse addNewUserV2(UserDto userDto){
//        Optional<User> userOptional = repository.findByUserName(userDto.getUserName());
//        if(userOptional.isPresent())
//            throw new UserNameAlreadyExist("User already exists!...");

//        User user = UserModelMapper.mapToUser(userDto);
//        User newUser = repository.save(user);
//        response.setStatusCode(HttpStatus.CREATED.toString())
//                .setIsOprSuccess(true)
//                .setResponseMsg("User has been added successfully with id : "+ user.getUserId() +"!...");
        try{
            User user = UserModelMapper.mapToUser(userDto);
            User newUser = userRepository.save(user);

            response = UserServiceOprResponse.createResponse()
                    .setStatusCode(HttpStatus.CREATED.toString())
                    .setIsOprSuccess(true)
                    .setResponseMsg("User has been added successfully with id : "+ user.getUserId() +"!...");
        }catch (DataIntegrityViolationException exception){
            throw new UserNameAlreadyExist("User already exists!...");
        }
        return response;
    }

    @Override
    @Transactional
    public UserServiceOprResponse updateUserDetails(UserDto userDto){

        try {
            Optional<User> userOptional = userRepository.findByUserName(userDto.getUserName());
            if(userOptional.isEmpty())
                throw new ResourceNotFound("User not found!.");

            User userToBeUpdated = UserModelMapper.mapToUser(userDto);
            userToBeUpdated.setUserName(userOptional.get().getUserName());
            userRepository.save(userToBeUpdated);

            response = UserServiceOprResponse.createResponse()
                    .setResponseMsg("User details has been updated successfully!.")
                    .setStatusCode(HttpStatus.ACCEPTED.toString())
                    .setIsOprSuccess(true);

        }catch (RuntimeException exception){
            throw new UserServiceException("Internal Server Error !-> " + exception.getMessage());
        }
        return response;
    }

    @Override
    @Transactional
    public ResponseEntity<UserServiceOprResponse> deleteUserByUserName(String userName){
        try{
            if(UserServiceUtils.isUserExist(userName, userRepository)){
                userRepository.deleteByUserName(userName);
            }
            response = UserServiceOprResponse.createResponse()
                    .setResponseMsg("User has been deleted successfully!..")
                    .setStatusCode(HttpStatus.ACCEPTED.toString())
                    .setIsOprSuccess(true);
        }catch (RuntimeException runtimeException){
            throw new UserServiceException(runtimeException.getMessage());
        }
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @Override
    @Transactional
    public ResponseEntity<UserServiceOprResponse> activateOrDeactivate(String userName, Boolean isActive) {
        Optional<User> users = userRepository.findByUserName(userName);
        if(users.isPresent()){
            User user = users.get();
            user.setIsUserActive(isActive);
            userRepository.deleteByUserName(userName);
            response = UserServiceOprResponse.createResponse()
                    .setStatusCode(HttpStatus.ACCEPTED.toString())
                    .setResponseMsg((isActive) ? "User has been deleted successfully!..."
                            : "User has been Deactivated!...")
                    .setIsOprSuccess(true);
        }else {
            throw new ResourceNotFound("User does not exists!...");
        }

        return new ResponseEntity<> (response, HttpStatus.ACCEPTED);
    }

    public ResponseEntity<UserServiceOprResponse> updateUserProfile(Long userId, UserDto userDetails){
        try {
            Optional<User> userOptional = UserServiceUtils.getUserDetailsWithId(userId, userRepository);
            if(userOptional.isEmpty())
                throw new ResourceNotFound("User not found!...");

            User user = UserModelMapper.mapToUser(userOptional.get(), userDetails);
            userRepository.save(user);
            response  = UserServiceOprResponse.createResponse()
                    .setResponseMsg("Updated Successfully")
                    .setStatusCode(HttpStatus.ACCEPTED.toString())
                    .setIsOprSuccess(true);
        }catch (Exception exception){
            throw new UserServiceException(exception.getMessage());
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
