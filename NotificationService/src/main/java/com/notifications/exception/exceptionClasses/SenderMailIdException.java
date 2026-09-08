package com.notifications.exception.exceptionClasses;

public class SenderMailIdException extends RuntimeException{
    String message;
    public SenderMailIdException(String message){
        super(message);
        this.message=message;
    }
}
