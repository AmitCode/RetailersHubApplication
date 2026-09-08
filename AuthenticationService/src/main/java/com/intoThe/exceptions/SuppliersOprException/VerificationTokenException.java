package com.intoThe.exceptions.SuppliersOprException;

public class VerificationTokenException extends RuntimeException{
    String message;
    public VerificationTokenException(String message){
        super(message);
        this.message = message;
    }
}
