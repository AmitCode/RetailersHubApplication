package com.intoThe.utils;

import com.intoThe.exceptions.SuppliersOprException.HashUtils256Exception;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtils {
    public static String getSHA256Hash(String token){
        byte[] hashBytes = getSHAByte(token);
        return getHashedString(hashBytes);
    }

    public static byte[] getSHAByte(String token){
        byte[] hashByte;
        try{
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            hashByte = messageDigest.digest(token.getBytes(StandardCharsets.UTF_8));

        }catch (NoSuchAlgorithmException exception){
            throw new HashUtils256Exception("[Exception occurs during hashing] {"+exception.getMessage()
            +"}");
        }
        return hashByte;
    }

    public static String getHashedString(byte[] hashBytes){
        StringBuilder hashedString = new StringBuilder();
        for (byte hBytes : hashBytes){
            hashedString.append(String.format("%02x", hBytes));
        }
        return hashedString.toString();
    }
}
