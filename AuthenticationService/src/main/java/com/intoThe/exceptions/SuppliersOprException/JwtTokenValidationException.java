package com.intoThe.exceptions.SuppliersOprException;

public class JwtTokenValidationException extends RuntimeException{
    String message;
    public JwtTokenValidationException(String message){
        super(message);
        this.message = message;
    }
}
