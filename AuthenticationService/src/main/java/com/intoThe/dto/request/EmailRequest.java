package com.intoThe.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class EmailRequest {
    private String userName;
    private String emailId;
    private String emailType;
    private String emailSubject;
    private String verificationUrl;
    private String tokenDuration;
    private String emailToken;

    public EmailRequest setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public EmailRequest setEmailId(String emailId) {
        this.emailId = emailId;
        return this;
    }

    public EmailRequest setEmailType(String emailType) {
        this.emailType = emailType;
        return this;
    }

    public EmailRequest setEmailSubject(String emailSubject) {
        this.emailSubject = emailSubject;
        return this;
    }

    public EmailRequest setVerificationUrl(String verificationUrl) {
        this.verificationUrl = verificationUrl;
        return this;
    }

    public EmailRequest setTokenDuration(String tokenDuration) {
        this.tokenDuration = tokenDuration;
        return this;
    }

    public static EmailRequest create(){
        return new EmailRequest();
    }

    public EmailRequest setEmailToken(String emailToken) {
        this.emailToken = emailToken;
        return this;
    }
}
