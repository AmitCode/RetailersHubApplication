package com.intoThe.utils;

import com.intoThe.dto.request.EmailRequest;
import com.intoThe.entities.Users;

public class AuthServiceUtils {
    public static EmailRequest prepareEmailRequest(Users user, String verificationToken, String emailType,
                                                   String subject) {
        return EmailRequest.create()
                .setEmailSubject(subject)
                .setEmailType(emailType)
                .setUserName(user.getUserName())
                .setEmailId(user.getUserEmail())
                .setVerificationUrl("http://localhost:8089/authService/verify/verifyUserAccount?verificationToken="+verificationToken);
    }

    public static EmailRequest preparePasswordResetEmailRequest(Users user, String verificationToken, String emailType){
        return EmailRequest.create()
                .setEmailSubject("Reset your password for your candidate account")
                .setEmailType(emailType)
                .setUserName(user.getUserName())
                .setEmailId(user.getUserEmail())
                .setVerificationUrl("http://localhost:8089/authService/userService/password-reset?password-reset-token="+verificationToken);
    }

    public static EmailRequest prepareSendOtpEmailRequest(String otp, String emailType, String subject,
                                                          String emailId){
        return EmailRequest.create()
                .setEmailId(emailId)
                .setEmailSubject(subject)
                .setEmailType(emailType)
                .setEmailToken(otp);
    }
}
