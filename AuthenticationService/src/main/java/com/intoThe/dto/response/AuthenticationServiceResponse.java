package com.intoThe.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class AuthenticationServiceResponse {
    private String statusCode = "";
    private String responseMsg = "";
    private Boolean isOprSuccess;

    public AuthenticationServiceResponse setStatusCode(String statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    public AuthenticationServiceResponse setResponseMsg(String responseMsg) {
        this.responseMsg = responseMsg;
        return this;
    }

    public AuthenticationServiceResponse setIsOprSuccess(Boolean isOprSuccess) {
        this.isOprSuccess = isOprSuccess;
        return this;
    }

    public static AuthenticationServiceResponse createResponse(){
        return new AuthenticationServiceResponse();
    }
}
