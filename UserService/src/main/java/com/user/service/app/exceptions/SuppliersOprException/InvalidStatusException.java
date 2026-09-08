package com.user.service.app.exceptions.SuppliersOprException;

public class InvalidStatusException extends RuntimeException{
    String message;
    public InvalidStatusException(String message){
        super(message);
        this.message = message;
    }
}
