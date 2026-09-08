package com.intoThe.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ApplicationConfig {

    //Why we need to do this because internal structure of this class is:-
    /*public class BCryptPasswordEncoder implements PasswordEncoder { }
    No @Service, @Component or the annotation that tells the spring to create the object of the bean like
    AddressService has @Service*/
    //We

    @Bean
    public PasswordEncoder passwordEncoder() {
/*        Passwords must NEVER be encrypted and stored. They must be hashed using a one-way algorithm like BCrypt.
                You should never decrypt a password, because hashed passwords cannot be reversed.*/
        return  new BCryptPasswordEncoder();
    }
}
