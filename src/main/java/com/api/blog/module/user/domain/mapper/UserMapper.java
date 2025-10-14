package com.api.blog.module.user.domain.mapper;

import com.api.blog.module.user.application.dto.create.UserCreateResponse;
import com.api.blog.module.user.domain.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserCreateResponse toCreateResponse(UserEntity user) {
        return new UserCreateResponse(user.getId(), user.getName().value(), user.getEmail().address(), user.getPassword(), user
                .getRole());
    }

}
