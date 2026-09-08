package com.intoThe.exceptions.SuppliersOprException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class EmailIdAlreadyExist extends RuntimeException{

    String message;
    public EmailIdAlreadyExist(String message){
        super(message);
        this.message = message;
    }
}
