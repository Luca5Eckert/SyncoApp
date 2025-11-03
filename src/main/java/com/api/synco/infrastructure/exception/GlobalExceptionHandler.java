package com.api.synco.infrastructure.exception;

import com.api.synco.infrastructure.api.CustomApiResponse;
import com.api.synco.module.authentication.domain.exception.AuthenticationException;
import com.api.synco.module.course.domain.exception.CourseException;
import com.api.synco.module.user.domain.exception.UserDomainException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserDomainException.class)
    public ResponseEntity<CustomApiResponse<?>> handlerUserException(UserDomainException e, HttpServletRequest httpServletRequest){
        String path = httpServletRequest.getRequestURI();

        return ResponseEntity.badRequest().body(CustomApiResponse.error(HttpStatus.BAD_REQUEST.value(), "USER_EXCEPTION", e.getMessage(), path));
    }

    @ExceptionHandler(CourseException.class)
    public ResponseEntity<CustomApiResponse<?>> handlerCourseException(CourseException e, HttpServletRequest httpServletRequest){
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
