package com.notifications.exception;

import com.notifications.dto.response.NotificationServiceExceptionResponse;
import com.notifications.exception.exceptionClasses.SenderMailIdException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class NotificationServiceException {
    @ExceptionHandler(SenderMailIdException.class)
    public ResponseEntity<NotificationServiceExceptionResponse> senderMailIdException(RuntimeException exception, WebRequest webRequest){
        return new ResponseEntity<>(new NotificationServiceExceptionResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                exception.getMessage(),
                LocalDateTime.now(),
                webRequest.getDescription(false)
        ), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
