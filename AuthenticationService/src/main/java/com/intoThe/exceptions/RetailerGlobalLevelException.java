package com.intoThe.exceptions;

import com.intoThe.controller.UserController;
import com.intoThe.errorResponse.RetailerExceptionResponse;
import com.intoThe.exceptions.SuppliersOprException.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;



@RestControllerAdvice
public class RetailerGlobalLevelException{
//public class RetailerGlobalLevelException extends Exception{
// do not extend Exception in case of global exception handler.

    /**
     * This method is responsible for catching all unchecked exceptions and returning
     * a ResponseEntity containing a RetailerExceptionResponse object with the
     * HTTP status set to INTERNAL_SERVER_ERROR.
     *
     * @param exception The exception that was thrown.
     * @param webRequest The web request that triggered the exception.
     * @return A ResponseEntity object containing the RetailerExceptionResponse
     *         object and the HTTP status code INTERNAL_SERVER_ERROR.
     */

    @ExceptionHandler(Exception.class)
    public ResponseEntity<RetailerExceptionResponse> handleGlobalException(Exception exception,
                                                                           WebRequest webRequest) {
        return new ResponseEntity<>(new RetailerExceptionResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Something went wrong: "+exception.getMessage(),
                webRequest.getDescription(false)),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * This method is responsible for catching all {@link HttpMessageNotReadableException}
     * and returning a ResponseEntity containing a RetailerExceptionResponse object with the
     * HTTP status set to BAD_REQUEST.
     *
     * @param messageException The exception that was thrown.
     * @param webRequest The web request that triggered the exception.
     * @return A ResponseEntity object containing the RetailerExceptionResponse
     *         object and the HTTP status code BAD_REQUEST.
     */

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<RetailerExceptionResponse> handleRequestCannotBeEmpty(HttpMessageNotReadableException
                                                                                messageException,
                                                                                WebRequest webRequest){

        return new ResponseEntity<>(new RetailerExceptionResponse(HttpStatus.BAD_REQUEST.value(),
                messageException.getMessage(),webRequest.getDescription(false)),
                HttpStatus.BAD_REQUEST);
    }

    /**
     * This method is used to handle {@link ResourceNotFoundException} exceptions thrown during the execution of any of the
     * {@link UserController} methods.
     *
     * @param resourceNotFound The {@link ResourceNotFoundException} exception to be handled.
     * @param request The {@link WebRequest} object containing information about the request that caused the exception.
     *
     * @return A {@link ResponseEntity} containing a {@link RetailerExceptionResponse} object with the HTTP status code
     *         set to {@link HttpStatus#NOT_FOUND}. The body of the ResponseEntity will contain the exception message
     *         and the description of the request that caused the exception.
     */

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<RetailerExceptionResponse> supplierNotFound(ResourceNotFoundException resourceNotFound,
                                                                      WebRequest request){
        return new ResponseEntity<>(new RetailerExceptionResponse(HttpStatus.NOT_FOUND.value(),
                resourceNotFound.getMessage(),request.getDescription(false)),
                HttpStatus.NOT_FOUND);
    }

    /**
     * This method is used to handle {@link EmailIdAlreadyExist} exceptions thrown during the execution of any of the
     * {@link UserController} methods.
     *
     * @param emailIdAlreadyExist The {@link EmailIdAlreadyExist} exception to be handled.
     * @param webRequest The {@link WebRequest} object containing information about the request that caused the exception.
     *
     * @return A {@link ResponseEntity} containing a {@link RetailerExceptionResponse} object with the HTTP status code
     *         set to {@link HttpStatus#BAD_REQUEST}. The body of the ResponseEntity will contain the exception message
     *         and the description of the request that caused the exception.
     */

    @ExceptionHandler(EmailIdAlreadyExist.class)
    public ResponseEntity<RetailerExceptionResponse> emailIdAlreadyExist(EmailIdAlreadyExist emailIdAlreadyExist,
                                                                         WebRequest webRequest){
        return new ResponseEntity<>(new RetailerExceptionResponse(HttpStatus.BAD_REQUEST.value(),
                emailIdAlreadyExist.getMessage(),webRequest.getDescription(false)),
                HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles {@link MethodArgumentNotValidException} exceptions thrown when the arguments passed to a controller
     * method do not pass validation.
     *
     * @param exception The {@link MethodArgumentNotValidException} to be handled.
     * @param webRequest The {@link WebRequest} object containing information about the request that caused the exception.
     *
     * @return A {@link ResponseEntity} containing a {@link RetailerExceptionResponse} object with the HTTP status code
     *         set to {@link HttpStatus#BAD_REQUEST}. The body of the ResponseEntity will contain the exception message
     *         and the description of the request that caused the exception.
     */

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RetailerExceptionResponse> handleMethodArgumentValidationException(
            MethodArgumentNotValidException exception, WebRequest webRequest){
        return new ResponseEntity<>(new RetailerExceptionResponse(
                    HttpStatus.BAD_REQUEST.value(),
                exception.getMessage(),
                webRequest.getDescription(false)),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNameAlreadyExist.class)
    public ResponseEntity<RetailerExceptionResponse> userNameAlreadyExist(UserNameAlreadyExist userNameAlreadyExist,
                                                                   WebRequest webRequest){
        return new ResponseEntity<>(new RetailerExceptionResponse(HttpStatus.BAD_REQUEST.value(),
                userNameAlreadyExist.getMessage(), webRequest.getDescription(false)),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNameNotFound.class)
    public ResponseEntity<RetailerExceptionResponse> userNotFound(UserNameNotFound exception,
                                                                           WebRequest webRequest) {
        return new ResponseEntity<>(new RetailerExceptionResponse(
                HttpStatus.NOT_FOUND.value(),
                exception.getMessage(),
                webRequest.getDescription(false)),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidCredentials.class)
    public ResponseEntity<RetailerExceptionResponse> invalidCredentials(InvalidCredentials exception,
                                                                  WebRequest webRequest) {
        return new ResponseEntity<>(new RetailerExceptionResponse(
                HttpStatus.UNAUTHORIZED.value(),
                exception.getMessage(),
                webRequest.getDescription(false)),HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UserInactiveException.class)
    public ResponseEntity<RetailerExceptionResponse> userInactiveException(UserInactiveException exception,
                                                                        WebRequest webRequest) {
        return new ResponseEntity<>(new RetailerExceptionResponse(
                HttpStatus.FORBIDDEN.value(),
                exception.getMessage(),
                webRequest.getDescription(false)),HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(JwtTokenGenerationException.class)
    public ResponseEntity<RetailerExceptionResponse> jwtTokenGenerationException(
            JwtTokenGenerationException exception, WebRequest webRequest) {
        return new ResponseEntity<>(new RetailerExceptionResponse(
                HttpStatus.FORBIDDEN.value(),
                exception.getMessage(),
                webRequest.getDescription(false)),HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(JwtTokenValidationException.class)
    public ResponseEntity<RetailerExceptionResponse> jwtTokenValidationException(
            JwtTokenValidationException exception, WebRequest webRequest) {
        return new ResponseEntity<>(new RetailerExceptionResponse(
                HttpStatus.FORBIDDEN.value(),
                exception.getMessage(),
                webRequest.getDescription(false)),HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(HashUtils256Exception.class)
    public ResponseEntity<RetailerExceptionResponse> hashUtils256Exception(
            HashUtils256Exception exception, WebRequest webRequest) {
        return new ResponseEntity<>(new RetailerExceptionResponse(
                HttpStatus.FORBIDDEN.value(),
                exception.getMessage(),
                webRequest.getDescription(false)),HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(InvalidToken.class)
    public ResponseEntity<RetailerExceptionResponse> invalidToken(
            InvalidToken exception, WebRequest webRequest) {
        return new ResponseEntity<>(new RetailerExceptionResponse(
                HttpStatus.FORBIDDEN.value(),
                exception.getMessage(),
                webRequest.getDescription(false)),HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(VerificationTokenException.class)
    public ResponseEntity<RetailerExceptionResponse> verificationTokenException(
            VerificationTokenException exception, WebRequest webRequest) {
        return new ResponseEntity<>(new RetailerExceptionResponse(
                HttpStatus.FORBIDDEN.value(),
                exception.getMessage(),
                webRequest.getDescription(false)),HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(VerificationTokenExpired.class)
    public ResponseEntity<RetailerExceptionResponse> verificationTokenExpiredException(
            VerificationTokenExpired exception, WebRequest webRequest) {
        return new ResponseEntity<>(new RetailerExceptionResponse(
                HttpStatus.FORBIDDEN.value(),
                exception.getMessage(),
                webRequest.getDescription(false)),HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<RetailerExceptionResponse> internalServerErrorException(
            InternalServerErrorException exception, WebRequest webRequest) {
        return new ResponseEntity<>(new RetailerExceptionResponse(
                HttpStatus.FORBIDDEN.value(),
                exception.getMessage(),
                webRequest.getDescription(false)),HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(PasswordMismatchException.class)
    public ResponseEntity<RetailerExceptionResponse> passwordMismatchException(
            PasswordMismatchException exception, WebRequest webRequest) {
        return new ResponseEntity<>(new RetailerExceptionResponse(
                HttpStatus.FORBIDDEN.value(),
                exception.getMessage(),
                webRequest.getDescription(false)),HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(OtpValidationException.class)
    public ResponseEntity<RetailerExceptionResponse> otpValidationException(
            OtpValidationException exception, WebRequest webRequest) {
        System.out.println("Message: " +exception.getMessage());
        return new ResponseEntity<>(new RetailerExceptionResponse(
                HttpStatus.FORBIDDEN.value(),
                exception.getMessage(),
                webRequest.getDescription(false)),HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(AccountInactiveException .class)
    public ResponseEntity<RetailerExceptionResponse> accountInactiveException(
            AccountInactiveException exception, WebRequest webRequest) {
        System.out.println("Message: " +exception.getMessage());
        return new ResponseEntity<>(new RetailerExceptionResponse(
                HttpStatus.FORBIDDEN.value(),
                exception.getMessage(),
                webRequest.getDescription(false)),HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<RetailerExceptionResponse> badCredentialsException(
            BadCredentialsException exception, WebRequest webRequest) {
        return new ResponseEntity<>(new RetailerExceptionResponse(
                HttpStatus.UNAUTHORIZED.value(),
                exception.getMessage(),
                webRequest.getDescription(false)),HttpStatus.UNAUTHORIZED);
    }

}
