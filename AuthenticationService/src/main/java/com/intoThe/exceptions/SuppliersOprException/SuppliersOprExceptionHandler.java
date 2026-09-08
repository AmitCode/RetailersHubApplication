package com.intoThe.exceptions.SuppliersOprException;

import com.intoThe.errorResponse.RetailerExceptionResponse;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;

@Component
@NoArgsConstructor
public class SuppliersOprExceptionHandler extends RuntimeException{

    String message;
    public SuppliersOprExceptionHandler(String message){
        super(message);
        this.message = message;
        System.out.println("Message: "+message);
    }

    /**
     * This method handles the case when a supplier is not found.
     * It creates a {@link RetailerExceptionResponse} object with the appropriate
     * HTTP status code and error message.
     *
     * @return a {@link ResponseEntity} object containing the {@link RetailerExceptionResponse}
     * object and the HTTP status code {@link HttpStatus#NOT_FOUND}.
     */

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<RetailerExceptionResponse> supplierNotFound(String message, String path){
        return new ResponseEntity<>(new RetailerExceptionResponse(HttpStatus.NOT_FOUND.value(),
                this.message,path), HttpStatus.NOT_FOUND);
    }
}
