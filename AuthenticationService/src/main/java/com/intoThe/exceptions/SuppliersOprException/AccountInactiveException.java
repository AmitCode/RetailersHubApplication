package com.intoThe.exceptions.SuppliersOprException;

public class AccountInactiveException extends RuntimeException{
    String message;
    public AccountInactiveException(String message){
        super(message);
        this.message = message;
    }
}
