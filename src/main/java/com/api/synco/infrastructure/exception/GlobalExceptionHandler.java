package com.api.synco.infrastructure.exception;

import com.api.synco.infrastructure.api.CustomApiResponse;
import com.api.synco.infrastructure.exception.token.TokenException;
import com.api.synco.module.authentication.domain.exception.AuthenticationException;
import com.api.synco.module.course.domain.exception.CourseDomainException;
import com.api.synco.module.user.domain.exception.UserDomainException;
import com.api.synco.module.user.domain.exception.UserNotFoundDomainException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Entitys

    @ExceptionHandler(UserDomainException.class)
    public ResponseEntity<CustomApiResponse<?>> handlerUserException(UserDomainException e, HttpServletRequest httpServletRequest){
        String path = httpServletRequest.getRequestURI();

        return ResponseEntity.badRequest().body(CustomApiResponse.error(HttpStatus.BAD_REQUEST.value(), "USER_EXCEPTION", e.getMessage(), path));
    }

    @ExceptionHandler(InternalAuthenticationServiceException.class)
    public ResponseEntity<CustomApiResponse<?>> handlerInternalAuthenticationException(InternalAuthenticationServiceException e, HttpServletRequest httpServletRequest) {
        String path = httpServletRequest.getRequestURI();

        HttpStatus status = HttpStatus.UNAUTHORIZED;

        return ResponseEntity.status(status).body(CustomApiResponse.error(
                status.value(),
                "AUTHENTICATION_SERVICE_ERROR",
                "Authentication failed: " + e.getMessage(),
                path)
        );
    }

    @ExceptionHandler(CourseDomainException.class)
    public ResponseEntity<CustomApiResponse<?>> handlerCourseException(CourseDomainException e, HttpServletRequest httpServletRequest){
        String path = httpServletRequest.getRequestURI();

        return ResponseEntity.badRequest().body(CustomApiResponse.error(HttpStatus.BAD_REQUEST.value(), "COURSE_EXCEPTION", e.getMessage(), path));
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<CustomApiResponse<?>> handlerAuthenticationException(AuthenticationException e, HttpServletRequest httpServletRequest){
        String path = httpServletRequest.getRequestURI();

        HttpStatus status = HttpStatus.UNAUTHORIZED;

        return ResponseEntity.status(status).body(CustomApiResponse.error(status.value(), "AUTHENTICATION_EXCEPTION", e.getMessage(), path));
    }

    @ExceptionHandler(TokenException.class)
    public ResponseEntity<CustomApiResponse<?>> handlerTokenException(TokenException tokenException, HttpServletRequest httpServletRequest){
        String path = httpServletRequest.getRequestURI();

        HttpStatus status = HttpStatus.UNAUTHORIZED;

        return ResponseEntity.status(status).body(CustomApiResponse.error(status.value(), "TOKEN_EXCEPTION", tokenException.getMessage(), path));
    }

    // Major Exceptions

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<CustomApiResponse<?>> handlerRuntimeException(RuntimeException e, HttpServletRequest httpServletRequest){
        String path = httpServletRequest.getRequestURI();

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        return ResponseEntity.status(status).body(CustomApiResponse.error(status.value(), "RUNTIME_EXCEPTION", "An unexpected error occurred", path));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomApiResponse<?>> handler(Exception e, HttpServletRequest httpServletRequest){
        String path = httpServletRequest.getRequestURI();

        return ResponseEntity.badRequest().body(CustomApiResponse.error(HttpStatus.BAD_REQUEST.value(), "EXCEPTION", "Generic error", path));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<CustomApiResponse<?>> handlerDataViolation(DataIntegrityViolationException dataIntegrityViolationException, HttpServletRequest httpServletRequest){
        String path = httpServletRequest.getRequestURI();

        HttpStatus status = HttpStatus.CONFLICT;

        return ResponseEntity.status(status).body(CustomApiResponse.error(status.value(), "DATA_INTEGRITY_VIOLATION", "The data integrity violation have been violation", path));
    }

}
