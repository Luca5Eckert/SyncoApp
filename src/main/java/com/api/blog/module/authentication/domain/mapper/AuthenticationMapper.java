package com.api.blog.module.authentication.domain.mapper;

import com.api.blog.infrastructure.security.user_details.UserDetailsImpl;
import com.api.blog.module.authentication.application.dto.login.UserLoginResponse;
import com.api.blog.module.authentication.application.dto.register.UserRegisterResponse;
import com.api.blog.module.user.domain.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationMapper {

    public UserRegisterResponse toRegisterResponse(UserEntity user) {
        return new UserRegisterResponse(user.getId(), user.getName().value(), user.getEmail().address(), user
                .getRole());
    }

    public UserLoginResponse toLoginResponse(UserDetailsImpl user, String token) {
        return new UserLoginResponse(
                user.getId()
                , user.getUsername()
                , user.getAuthorities()
                , token);
    }
}
