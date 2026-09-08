package com.example;


import com.intoThe.entities.Users;
import com.intoThe.mapper.UserDataModelMapper;
import com.intoThe.repository.UserRepository;
import com.intoThe.service.UserService;
import com.intoThe.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.mockito.Mockito.*;


@SpringBootTest(classes = UserServiceTestClass.class)
public class UserServiceTestClass {
//
//    UserService userService;
//    UserRepository userRepository = Mockito.mock(UserRepository.class);
//    UserDataModelMapper userDataModelMapper = new UserDataModelMapper();
//    //Creates the mock version of our repository.
//
//    @BeforeEach
//    public void setup() {
//        userService = new UserServiceImpl(userRepository);
//    }
//
//    @Test
//    public void addUserTest() {
//        Users user = new Users();
//        user.setUserFirstName("Amit");
//        user.setUserMiddleName("Kumar");
//        user.setUserLastName("Pandey");
//        user.setUserEmailId("amit.pandey05@gmail.com");
//        user.setUserContactNumber("34736676762354675");
//
//        when(userRepository.findByUserEmailId(user.getUserEmailId()))
//                .thenReturn(null);
//        when(userRepository.save(any(Users.class))).thenReturn(user);
//        //return message;
//
//        String result = userService.addUser(userDataModelMapper.mapToUserDTO(user));
//
//        assertNotNull("Data has been saved successfully");
//    }
}
