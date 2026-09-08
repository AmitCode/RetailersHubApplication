package com.user.service.app.exceptions.SuppliersOprException;

public class UserServiceException extends RuntimeException{
    String message;
    public UserServiceException(String msg){
        super(msg);
        this.message = msg;
    }
}
