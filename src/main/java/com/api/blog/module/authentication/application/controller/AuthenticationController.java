package com.api.blog.module.authentication.application.controller;

import com.api.blog.module.authentication.application.dto.login.UserLoginRequest;
import com.api.blog.module.authentication.application.dto.login.UserLoginResponse;
import com.api.blog.module.authentication.application.dto.register.UserRegisterRequest;
import com.api.blog.module.authentication.application.dto.register.UserRegisterResponse;
import com.api.blog.module.authentication.domain.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/blog/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserRegisterResponse> register(@RequestBody @Valid UserRegisterRequest userRegisterRequest){
        var user = authenticationService.register(userRegisterRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponse> login(@RequestBody @Valid UserLoginRequest userLoginRequest){
        var user = authenticationService.login(userLoginRequest);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(user);
    }

}
