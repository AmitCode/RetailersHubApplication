package com.user.service.app.utils;

import com.user.service.app.entities.User;
import com.user.service.app.repositories.UserRepository;

import java.util.Optional;

public class UserServiceUtils {
    public static boolean isUserExist(String userName, UserRepository userRepository){
        Optional<User> userOptional = userRepository.findByUserName(userName);
        return userOptional.isPresent();
    }

    public static boolean isEmailExist(String userEmail, UserRepository repository){
        Optional<User> userOptional = repository.findByUserEmailId(userEmail);
        return userOptional.isPresent();
    }

    public static Optional<User> getUserDetailsWithId(Long userId, UserRepository userRepository){
        return userRepository.findById(userId);
    }
}
