package com.intoThe.mapper;

import com.intoThe.entities.EntityVerificationToken;
import com.intoThe.enums.TokenExpirationUnit;

public class VerificationTokenModelMapper {
    public static EntityVerificationToken getVerificationToken(String token, String tokeHashType,
                                                               Long userId, String userName,
                                                               int tokenDuration, TokenExpirationUnit tokenValidUnit){
        return EntityVerificationToken.createEntityVerificationToken()
                .setVerificationToken(token)
                .setTokenHashType(tokeHashType)
                .setUserId(userId)
                .setTokenValidDuration(tokenDuration)
                .setTokenValidDurationUnit(tokenValidUnit);
    }
}
