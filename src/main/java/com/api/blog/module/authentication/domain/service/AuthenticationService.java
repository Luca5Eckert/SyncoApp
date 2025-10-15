package com.api.blog.module.authentication.domain.service;

import com.api.blog.module.authentication.application.dto.register.UserRegisterRequest;
import com.api.blog.module.authentication.application.dto.register.UserRegisterResponse;
import com.api.blog.module.authentication.domain.mapper.AuthenticationMapper;
import com.api.blog.module.authentication.domain.use_case.UserRegisterUseCase;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final AuthenticationMapper authenticationMapper;

    private final UserRegisterUseCase registerUseCase;


    public AuthenticationService(AuthenticationMapper authenticationMapper, UserRegisterUseCase registerUseCase) {
        this.authenticationMapper = authenticationMapper;
        this.registerUseCase = registerUseCase;
    }

    public UserRegisterResponse register(@Valid UserRegisterRequest userRegisterRequest) {
        var user = registerUseCase.execute(userRegisterRequest);
        return authenticationMapper.toRegisterResponse(user);
    }
}
