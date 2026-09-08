package com.intoThe.exceptions.SuppliersOprException;

public class InternalServerErrorException extends RuntimeException{
    String message;
    public InternalServerErrorException(String message){
        super(message);
        this.message = message;
    }
}
