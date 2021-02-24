package com.login.demo.exception.handler;


import com.login.demo.exception.UserLoginException;
import com.login.demo.exception.model.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class AuthControllerAdvice {

    private final Logger logger = LoggerFactory.getLogger(AuthControllerAdvice.class);

    @ExceptionHandler(value = UserLoginException.class)
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    @ResponseBody
    public ErrorResponse handleUserLoginException(UserLoginException ex, WebRequest request) {
        return new ErrorResponse(HttpStatus.EXPECTATION_FAILED, ex.getMessage());
    }

    @ExceptionHandler(value = BadCredentialsException.class)
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    @ResponseBody
    public ErrorResponse handleBadCredentialsException(BadCredentialsException ex, WebRequest request) {
        return new ErrorResponse(HttpStatus.EXPECTATION_FAILED, ex.getMessage());
    }
}
