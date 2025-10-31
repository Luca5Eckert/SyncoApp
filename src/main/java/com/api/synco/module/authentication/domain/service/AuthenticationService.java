package com.api.synco.module.authentication.domain.service;

import com.api.synco.module.authentication.application.dto.login.UserLoginRequest;
import com.api.synco.module.authentication.application.dto.login.UserLoginResponse;
import com.api.synco.module.authentication.application.dto.register.UserRegisterRequest;
import com.api.synco.module.authentication.application.dto.register.UserRegisterResponse;
import com.api.synco.module.authentication.application.dto.reset_password.UserResetRequest;
import com.api.synco.module.authentication.domain.mapper.AuthenticationMapper;
import com.api.synco.module.authentication.domain.use_case.UserLoginUseCase;
import com.api.synco.module.authentication.domain.use_case.UserRegisterUseCase;
import com.api.synco.module.authentication.domain.use_case.UserResetPasswordUseCase;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final AuthenticationMapper authenticationMapper;

    private final UserRegisterUseCase registerUseCase;
    private final UserLoginUseCase loginUseCase;
    private final UserResetPasswordUseCase userResetPasswordUseCase;

    public AuthenticationService(AuthenticationMapper authenticationMapper, UserRegisterUseCase registerUseCase, UserLoginUseCase loginUseCase, UserResetPasswordUseCase userResetPasswordUseCase) {
        this.authenticationMapper = authenticationMapper;
        this.registerUseCase = registerUseCase;
        this.loginUseCase = loginUseCase;
        this.userResetPasswordUseCase = userResetPasswordUseCase;
    }

    public UserRegisterResponse register(@Valid UserRegisterRequest userRegisterRequest) {
        var user = registerUseCase.execute(userRegisterRequest);
        return authenticationMapper.toRegisterResponse(user);
    }

    public UserLoginResponse login(UserLoginRequest userLoginRequest) {
        return loginUseCase.execute(userLoginRequest);
    }

    public void resetPassword(UserResetRequest userResetRequest, long idUser) {
        userResetPasswordUseCase.execute(userResetRequest, idUser);
    }

}
