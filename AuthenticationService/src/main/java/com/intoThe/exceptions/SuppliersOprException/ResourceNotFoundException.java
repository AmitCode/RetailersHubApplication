package com.intoThe.exceptions.SuppliersOprException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends  RuntimeException {
    String message;

    public ResourceNotFoundException(String message) {

        super(message);
        System.out.println("Here!....");
        this.message = message;
    }
}
