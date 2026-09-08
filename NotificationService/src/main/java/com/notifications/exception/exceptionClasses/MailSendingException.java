package com.notifications.exception.exceptionClasses;

public class MailSendingException extends RuntimeException{
    String message;
    public MailSendingException(String message){
        super(message);
        this.message=message;
    }
}
