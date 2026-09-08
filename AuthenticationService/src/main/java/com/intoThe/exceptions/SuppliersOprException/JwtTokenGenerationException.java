package com.intoThe.exceptions.SuppliersOprException;

public class JwtTokenGenerationException extends RuntimeException{
    String message;
    public JwtTokenGenerationException(String message){
        super(message);
        this.message = message;
    }
}
