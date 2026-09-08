package com.intoThe.exceptions.SuppliersOprException;

public class UserInactiveException extends RuntimeException{
    String message;
    public UserInactiveException(String message){
        super(message);
        this.message  = message;
    }
}
