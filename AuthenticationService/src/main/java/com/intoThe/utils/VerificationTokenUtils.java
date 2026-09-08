package com.intoThe.utils;

import com.intoThe.enums.TokenExpirationUnit;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Base64;

public class VerificationTokenUtils {
    private static final SecureRandom secureRandomNumber = new SecureRandom();

    public static String generateVerificationToken(){
        byte[] tokenBytes = new byte[32];
        secureRandomNumber.nextBytes(tokenBytes);
        return Base64.getUrlEncoder()
                .withoutPadding()
                .encodeToString(tokenBytes);
    }

    public static boolean isTokenExpired(LocalDateTime currentTokenDate, String tokenExpiryTime) throws Exception {
        long durationInMinutes = CustomDateTime.findTheDifferenceInMinutes(currentTokenDate);
        return (Long.parseLong(tokenExpiryTime) < durationInMinutes);
    }

    public static boolean isTokenExpired(LocalDateTime currentTokenDate, int otpValidDuration,
                                         String tokenExpirationUnit) {
        long durationInMinutes = 0;
        System.out.println("***");
        try{
            if(tokenExpirationUnit.equalsIgnoreCase(TokenExpirationUnit.MINUTES.name()))
                durationInMinutes = CustomDateTime.findTheDifferenceInMinutes(currentTokenDate);
            else if(tokenExpirationUnit.equalsIgnoreCase(TokenExpirationUnit.HOURS.name()))
                durationInMinutes = CustomDateTime.findTheDifferenceInHours(currentTokenDate);
            else if(tokenExpirationUnit.equalsIgnoreCase(TokenExpirationUnit.DAYS.name()))
                durationInMinutes = CustomDateTime.findTheDifferenceInDays(currentTokenDate);
            else if(tokenExpirationUnit.equalsIgnoreCase(TokenExpirationUnit.SECONDS.name()))
                durationInMinutes = CustomDateTime.findTheDifferenceInSeconds(currentTokenDate);
            else if(tokenExpirationUnit.equalsIgnoreCase(TokenExpirationUnit.YEARS.name()))
                durationInMinutes = CustomDateTime.findTheDifferenceInYears(currentTokenDate);
        }catch (Exception exception){
            System.out.println("[Validation]-{Exception} -Ex:- " +exception.getMessage());
        }
        return (Long.parseLong(otpValidDuration+"") < durationInMinutes);
    }

}
