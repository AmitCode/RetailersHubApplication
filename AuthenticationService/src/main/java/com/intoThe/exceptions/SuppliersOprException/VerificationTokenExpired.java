package com.intoThe.exceptions.SuppliersOprException;

public class VerificationTokenExpired extends RuntimeException{
    String message;
    public VerificationTokenExpired(String message){
        this.message = message;
    }
}
