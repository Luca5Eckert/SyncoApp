package com.api.blog.infrastructure.exception;

import com.api.blog.infrastructure.api.CustomApiResponse;
import com.api.blog.module.authentication.domain.exception.AuthenticationException;
import com.api.blog.module.user.domain.exception.UserException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomApiResponse<?>> handler(Exception e, HttpServletRequest httpServletRequest){
        String path = httpServletRequest.getRequestURI();

        return switch (e) {
            case UserException userException -> {
                log.error("User exception at path {}: {}", path, userException.getMessage());
                yield ResponseEntity.ok(CustomApiResponse.error(HttpStatus.BAD_REQUEST.value(), "USER_EXCEPTION", userException.getMessage(), path));
            }

            case AuthenticationException authenticationException -> {
                log.error("Authentication exception at path {}: {}", path, authenticationException.getMessage());
                yield ResponseEntity.ok(CustomApiResponse.error(HttpStatus.BAD_REQUEST.value(), "AUTHENTICATION_EXCEPTION", authenticationException.getMessage(), path));
            }

            case RuntimeException runtimeException -> {
                log.error("Runtime exception at path {}: {}", path, runtimeException.getMessage(), runtimeException);
                yield ResponseEntity.ok(CustomApiResponse.error(HttpStatus.BAD_REQUEST.value(), "RUNTIME_EXCEPTION", runtimeException.getMessage(), path));
            }

            case null, default -> {
                log.error("Unexpected exception at path {}: {}", path, e != null ? e.getMessage() : "null exception", e);
                yield ResponseEntity.ok(CustomApiResponse.error(HttpStatus.BAD_REQUEST.value(), "EXCEPTION", "Generic error", path));
            }

        };


    }



}
