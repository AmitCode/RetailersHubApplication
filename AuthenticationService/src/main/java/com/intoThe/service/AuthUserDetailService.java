package com.intoThe.service;

import com.intoThe.dto.request.UserLoginRequest;
import com.intoThe.entities.Users;
import com.intoThe.exceptions.SuppliersOprException.*;
import com.intoThe.repository.UserRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthUserDetailService implements UserDetailsService {
    private final UserRepository repository;
    public AuthUserDetailService(UserRepository repository){
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(@NotNull String username) throws UsernameNotFoundException {
        Users user = repository.findByUserName(username)
                .orElseThrow(() -> new UserNameNotFound("User does not exists!."));

        checkIsUserActive(user);
        return new User(user.getUserName(), user.getPassword(),
                List.of(new SimpleGrantedAuthority("Admin")));
    }

    private void checkIsUserActive(Users user){
        if(!user.getIsUserActive())
            throw new AccountInactiveException("Account is inactive!");
    }
}
