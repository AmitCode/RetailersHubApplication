package com.intoThe.exceptions.SuppliersOprException;

public class InvalidCredentials extends RuntimeException{
    String message;
    public InvalidCredentials(String message){
        super(message);
        this.message = message;
    }
}
