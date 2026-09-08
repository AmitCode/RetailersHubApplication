package com.intoThe.exceptions.SuppliersOprException;

public class HashUtils256Exception extends RuntimeException{
    String message;
    public HashUtils256Exception(String message){
        super(message);
        this.message = message;
    }
}
