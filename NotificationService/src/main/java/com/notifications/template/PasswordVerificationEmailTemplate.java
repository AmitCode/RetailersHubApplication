package com.notifications.template;

public class PasswordVerificationEmailTemplate {
    public static String generatePasswordResetEmailTemplate(String verificationToken, String tokenDuration){
        return String.format("Click this link to reset your password" +
                "Link: <a href=\"%s\">Click Here</a>" +
                "The link will expire after %s hours.", verificationToken, tokenDuration);
    }
}
