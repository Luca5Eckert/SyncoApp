package com.api.blog.infrastructure.exception;

import com.api.blog.infrastructure.api.CustomApiResponse;
import com.api.blog.module.authentication.domain.exception.AuthenticationException;
import com.api.blog.module.user.domain.exception.UserException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserException.class)
    public ResponseEntity<CustomApiResponse<?>> handlerUserException(UserException e, HttpServletRequest httpServletRequest){
        String path = httpServletRequest.getRequestURI();

        return ResponseEntity.badRequest().body(CustomApiResponse.error(HttpStatus.BAD_REQUEST.value(), "USER_EXCEPTION", e.getMessage(), path));
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<CustomApiResponse<?>> handlerAuthenticationException(AuthenticationException e, HttpServletRequest httpServletRequest){
        String path = httpServletRequest.getRequestURI();

        return ResponseEntity.badRequest().body(CustomApiResponse.error(HttpStatus.BAD_REQUEST.value(), "AUTHENTICATION_EXCEPTION", e.getMessage(), path));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<CustomApiResponse<?>> handlerUserException(RuntimeException e, HttpServletRequest httpServletRequest){
        String path = httpServletRequest.getRequestURI();

        return ResponseEntity.badRequest().body(CustomApiResponse.error(HttpStatus.BAD_REQUEST.value(), "RUNTIME_EXCEPTION", e.getMessage(), path));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomApiResponse<?>> handler(Exception e, HttpServletRequest httpServletRequest){
        String path = httpServletRequest.getRequestURI();

        return ResponseEntity.badRequest().body(CustomApiResponse.error(HttpStatus.BAD_REQUEST.value(), "EXCEPTION", "Generic error", path));
    }

}
