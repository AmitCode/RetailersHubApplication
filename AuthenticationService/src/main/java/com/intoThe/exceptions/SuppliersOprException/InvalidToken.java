package com.intoThe.exceptions.SuppliersOprException;

public class InvalidToken extends RuntimeException{
    String message;
    public InvalidToken(String message){
        super(message);
        this.message = message;
    }
}
