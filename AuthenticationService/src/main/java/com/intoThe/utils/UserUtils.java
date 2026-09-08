package com.intoThe.utils;

import com.intoThe.entities.Users;
import com.intoThe.exceptions.SuppliersOprException.EmailIdAlreadyExist;
import com.intoThe.exceptions.SuppliersOprException.ResourceNotFoundException;
import com.intoThe.exceptions.SuppliersOprException.SuppliersOprExceptionHandler;
import com.intoThe.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class UserUtils {

    /**
     * Checks if a user exists in the database.
     *
     * @param userName The ID of the user to be checked.
     * @param userRepository The repository to use for the check.
     * @return The user object if the user exists, null otherwise.
     * @throws SuppliersOprExceptionHandler if the user does not exist.
     */
    public static Users isUserExist(String userName, UserRepository userRepository) {

        return userRepository.findByUserName(userName)
                .orElseThrow(() -> (new ResourceNotFoundException("User with this id :" + userName + " not found!...")));
    }

    /**
     * Checks if a user with the given email id exists in the database.
     *
     * @param userEmailId The email id of the user to be checked.
     * @param userRepository The repository to use for the check.
     * @return true if the user exists, false otherwise.
     * @throws EmailIdAlreadyExist if the user exists.
     */

    public static Boolean isUserExistWithEmail(String userEmailId,UserRepository userRepository){
        return userRepository.findByUserName(userEmailId).isPresent();
    }

    public static Boolean isUserNameAlreadyExist(String userName, UserRepository userRepository){
        return userRepository.findByUserName(userName).isPresent();
    }
}
