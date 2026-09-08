package com.intoThe.dto.response;


import com.intoThe.validation.annoation.AtLeastOneRequired;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UserLoginResponse {
    private String isLoginSuccess;
    private String loginMessage;
    private String statusCode;

    public UserLoginResponse setIsLoginSuccess(String isLoginSuccess) {
        this.isLoginSuccess = isLoginSuccess;
        return this;
    }

    public UserLoginResponse setLoginMessage(String loginMessage) {
        this.loginMessage = loginMessage;
        return this;
    }

    public UserLoginResponse setStatusCode(String statusCode) {
        this.statusCode = statusCode;
        return this;
    }
}
