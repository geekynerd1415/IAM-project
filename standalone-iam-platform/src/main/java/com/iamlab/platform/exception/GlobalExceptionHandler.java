package com.iamlab.platform.exception;

import com.iamlab.platform.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleUserNotFound(UserNotFoundException exception) {
        return new ErrorResponse(
                404,
                "USER_NOT_FOUND",
                exception.getMessage());
    }
    
    @ExceptionHandler(DuplicateEmailException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleDuplicateEmail(
            DuplicateEmailException exception) {
        return new ErrorResponse(
                409,
                "EMAIL_ALREADY_EXISTS",
                exception.getMessage());
    }
}

// package com.iamlab.platform.exception;

// import org.springframework.http.HttpStatus;
// import org.springframework.web.bind.annotation.ExceptionHandler;
// import org.springframework.web.bind.annotation.ResponseStatus;
// import org.springframework.web.bind.annotation.RestControllerAdvice;

// @RestControllerAdvice
// public class GlobalExceptionHandler {

// @ExceptionHandler(UserNotFoundException.class)
// @ResponseStatus(HttpStatus.NOT_FOUND)
// public String handleUserNotFound(UserNotFoundException exception) {
// return exception.getMessage();
// }
// }