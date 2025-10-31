package com.api.synco.module.user.domain.mapper;

import com.api.synco.module.user.application.dto.create.UserCreateResponse;
import com.api.synco.module.user.application.dto.edit.UserEditResponse;
import com.api.synco.module.user.application.dto.get.UserGetResponse;
import com.api.synco.module.user.domain.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserCreateResponse toCreateResponse(UserEntity user) {
        return new UserCreateResponse(user.getId(), user.getName().value(), user.getEmail().address(), user
                .getRole());
    }

    public UserEditResponse toEditResponse(UserEntity user) {
        return new UserEditResponse(user.getId(), user.getName().value(), user.getEmail().address());
    }

    public UserGetResponse toGetResponse(UserEntity user) {
        return new UserGetResponse(
                user.getId(),
                user.getName().value(),
                user.getEmail().address(),
                user.getRole(),
                user.getCreateAt(),
                user.getUpdateAt()
        );
    }


}
