package com.api.blog.module.user.domain.service;

import com.api.blog.module.user.application.dto.create.UserCreateRequest;
import com.api.blog.module.user.application.dto.create.UserCreateResponse;
import com.api.blog.module.user.application.dto.delete.UserDeleteRequest;
import com.api.blog.module.user.application.dto.edit.UserEditRequest;
import com.api.blog.module.user.application.dto.edit.UserEditResponse;
import com.api.blog.module.user.domain.mapper.UserMapper;
import com.api.blog.module.user.domain.use_case.UserCreateUseCase;
import com.api.blog.module.user.domain.use_case.UserDeleteUseCase;
import com.api.blog.module.user.domain.use_case.UserEditUseCase;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserMapper userMapper;

    private final UserCreateUseCase userCreateUseCase;
    private final UserDeleteUseCase userDeleteUseCase;
    private final UserEditUseCase userEditUseCase;

    public UserService(UserMapper userMapper, UserCreateUseCase userCreateUseCase, UserDeleteUseCase userDeleteUseCase, UserEditUseCase userEditUseCase) {
        this.userMapper = userMapper;
        this.userCreateUseCase = userCreateUseCase;
        this.userDeleteUseCase = userDeleteUseCase;
        this.userEditUseCase = userEditUseCase;
    }

    public UserCreateResponse create(UserCreateRequest userCreateRequest) {
        var user = userCreateUseCase.execute(userCreateRequest);
        return userMapper.toCreateResponse(user);
    }

    public void delete(UserDeleteRequest userDeleteRequest, long idUserAutenticated) {
        userDeleteUseCase.execute(userDeleteRequest, idUserAutenticated);
    }

    public UserEditResponse edit(UserEditRequest userEditRequest, long idUserAutenticated) {
        var user = userEditUseCase.execute(userEditRequest, idUserAutenticated);
        return userMapper.toEditResponse(user);
    }

}
