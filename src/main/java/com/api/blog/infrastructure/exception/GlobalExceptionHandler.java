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

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomApiResponse<?>> handler(Exception e, HttpServletRequest httpServletRequest){
        String path = httpServletRequest.getRequestURI();

        return switch (e) {
            case UserException userException ->
                    ResponseEntity.badRequest().body(CustomApiResponse.error(HttpStatus.BAD_REQUEST.value(), "USER_EXCEPTION", userException.getMessage(), path));

            case AuthenticationException authenticationException ->
                    ResponseEntity.badRequest().body(CustomApiResponse.error(HttpStatus.BAD_REQUEST.value(), "AUTHENTICATION_EXCEPTION", authenticationException.getMessage(), path));

            case RuntimeException runtimeException ->
                    ResponseEntity.badRequest().body(CustomApiResponse.error(HttpStatus.BAD_REQUEST.value(), "RUNTIME_EXCEPTION", runtimeException.getMessage(), path));

            case null, default ->
                    ResponseEntity.badRequest().body(CustomApiResponse.error(HttpStatus.BAD_REQUEST.value(), "EXCEPTION", "Generic error", path));

        };


    }





}
