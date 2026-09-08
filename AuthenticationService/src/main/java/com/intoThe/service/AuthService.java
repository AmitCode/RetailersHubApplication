package com.intoThe.service;

import com.intoThe.dto.request.UserLoginRequest;
import com.intoThe.dto.response.UserLoginResponse;

public interface AuthService {
    public void userLogin(UserLoginRequest loginRequest);
}
