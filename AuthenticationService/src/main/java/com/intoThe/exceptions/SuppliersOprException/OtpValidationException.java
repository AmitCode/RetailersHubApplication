package com.intoThe.exceptions.SuppliersOprException;

public class OtpValidationException extends RuntimeException{
    String message;
    public OtpValidationException(String message){
        super(message);
        this.message = message;
    }
}
