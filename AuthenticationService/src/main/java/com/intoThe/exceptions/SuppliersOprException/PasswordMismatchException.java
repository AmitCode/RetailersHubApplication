package com.intoThe.exceptions.SuppliersOprException;

public class PasswordMismatchException extends Exception{
    String message;
    public PasswordMismatchException(String message){
        this.message = message;
    }
}
