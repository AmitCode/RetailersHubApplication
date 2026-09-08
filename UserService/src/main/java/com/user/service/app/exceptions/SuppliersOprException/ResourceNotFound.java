package com.user.service.app.exceptions.SuppliersOprException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFound extends  RuntimeException {
    String message;

    public ResourceNotFound(String message) {

        super(message);
        System.out.println("Here!....");
        this.message = message;
    }
}
