package com.intoThe.service.impl;

import com.intoThe.dto.request.UserLoginRequest;
import com.intoThe.dto.response.UserLoginResponse;
import com.intoThe.entities.Users;
import com.intoThe.exceptions.SuppliersOprException.InvalidCredentials;
import com.intoThe.exceptions.SuppliersOprException.ResourceNotFoundException;
import com.intoThe.exceptions.SuppliersOprException.UserInactiveException;
import com.intoThe.exceptions.SuppliersOprException.UserNameNotFound;
import com.intoThe.repository.UserRepository;
import com.intoThe.service.AuthService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    //@Autowired ----> No @Autowired needed — ONLY ONE constructor
    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void userLogin(UserLoginRequest loginRequest) {
        UserLoginResponse userLoginResponse = new UserLoginResponse();

        System.out.println(loginRequest.getUserName());
        Users user = userRepository.findByUserName(loginRequest.getUserName())
                .orElseThrow(()-> new ResourceNotFoundException("User not found!"));

        checkIsUserActive(user);
        checkPassword(user, loginRequest);
    }

    private void checkIsUserActive(Users user){
        if(!user.getIsUserActive())
            throw new UserInactiveException("User account is inactive");
    }

    private void checkPassword(Users user, UserLoginRequest loginRequest){
        boolean isPasswordMatched = passwordEncoder.matches(
                loginRequest.getPassword(),
                user.getPassword()
        );
        if(!isPasswordMatched){
            throw new InvalidCredentials("Invalid Credentials!");
        }
    }

    private boolean duplicateLoginCheck(){

        return true;
    }
}
