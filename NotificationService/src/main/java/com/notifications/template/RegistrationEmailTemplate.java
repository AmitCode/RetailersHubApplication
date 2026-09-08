package com.notifications.template;

public class RegistrationEmailTemplate {
    public static String generateRegistrationEmailTemplate(String userName, String userEmail,
                                                           String verifyLinkUrl, String tokenDuration){
        return String.format("Click below link to verify and complete your user account registration." +
                "<a href=\"%s\">Click Here!.</a> " +
                "The link will expire after %s hours.", verifyLinkUrl, tokenDuration);
    }
}
