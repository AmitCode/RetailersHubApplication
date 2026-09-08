package com.intoThe.utils;

import com.intoThe.exceptions.SuppliersOprException.JwtTokenGenerationException;
import com.intoThe.exceptions.SuppliersOprException.JwtTokenValidationException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JWTUtils {
    private final SecretKey secreteKey = Keys.hmacShaKeyFor("my-super-secure-secret-key-1234567890".getBytes());

    public String generateJwtToken(String userName){
        String token = "";
        try{
            token = Jwts.builder()
                    .setSubject(userName)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + 1000 + 60 + 60)) // This sets expiration
                    // time for the token. This token will become invalid after a fixed duration.
                    //.setClaims("userName" , userName)
                    .signWith(secreteKey)
                    .compact();
        }catch (Exception exception){
            throw new JwtTokenGenerationException("Error while token generation[" + exception.getMessage()+ "]");
        }
        return token;
    }

    public String getUserNameFromToken(String token){
        return getClaims(token).getSubject();
    }

    public Claims getClaims(String token){
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(secreteKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception exception){
            throw new JwtTokenValidationException(
                    "Error while extracting the username [" + exception.getMessage() + "]");
        }
    }

    public Boolean validateToken(String token, String userName){
        try{
            String tokenUsername = getUserNameFromToken(token);
            if(tokenUsername.equalsIgnoreCase(userName)){
                return true;
            }
        }catch (Exception exception){
            throw new JwtTokenValidationException("Invalid token!.");
        }
        return false;
    }
}
