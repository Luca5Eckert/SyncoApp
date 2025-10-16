package com.api.blog.module.authentication.domain.use_case;

import com.api.blog.infrastructure.security.jwt.JwtTokenProvider;
import com.api.blog.infrastructure.security.user_details.UserDetailsImpl;
import com.api.blog.module.authentication.application.dto.login.UserLoginRequest;
import com.api.blog.module.authentication.application.dto.login.UserLoginResponse;
import com.api.blog.module.authentication.domain.exception.AuthenticationValidationException;
import com.api.blog.module.authentication.domain.mapper.AuthenticationMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class UserLoginUseCase {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    private final AuthenticationMapper authenticationMapper;

    public UserLoginUseCase(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, AuthenticationMapper authenticationMapper) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationMapper = authenticationMapper;
    }


    public UserLoginResponse execute(UserLoginRequest userLoginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLoginRequest.email(), userLoginRequest.password()));

        if(!authentication.isAuthenticated()){
            throw new AuthenticationValidationException();
        }

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        String token = jwtTokenProvider.generateToken(userDetails.getUsername());

        return authenticationMapper.toLoginResponse(userDetails, token);
    }

}
