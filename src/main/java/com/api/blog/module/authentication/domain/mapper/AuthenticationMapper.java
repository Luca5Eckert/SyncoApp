package com.api.blog.module.authentication.domain.mapper;

import com.api.blog.module.authentication.application.dto.register.UserRegisterResponse;
import com.api.blog.module.user.domain.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationMapper {

    public UserRegisterResponse toRegisterResponse(UserEntity user) {
        return new UserRegisterResponse(user.getId(), user.getName().value(), user.getEmail().address(), user
                .getRole());
    }

}
