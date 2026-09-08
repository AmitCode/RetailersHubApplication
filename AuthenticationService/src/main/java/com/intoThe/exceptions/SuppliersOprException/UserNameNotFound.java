package com.intoThe.exceptions.SuppliersOprException;

public class UserNameNotFound extends RuntimeException{
    String message;
    public UserNameNotFound(String msg){
        super(msg);
        this.message = msg;
    }
}
