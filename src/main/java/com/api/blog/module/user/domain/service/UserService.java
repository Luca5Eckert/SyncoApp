package com.api.blog.module.user.domain.service;

import com.api.blog.module.user.application.dto.create.UserCreateRequest;
import com.api.blog.module.user.application.dto.create.UserCreateResponse;
import com.api.blog.module.user.application.dto.delete.UserDeleteRequest;
import com.api.blog.module.user.domain.mapper.UserMapper;
import com.api.blog.module.user.domain.use_case.UserCreateUseCase;
import com.api.blog.module.user.domain.use_case.UserDeleteUseCase;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserMapper userMapper;

    private final UserCreateUseCase userCreateUseCase;
    private final UserDeleteUseCase userDeleteUseCase;

    public UserService(UserMapper userMapper, UserCreateUseCase userCreateUseCase, UserDeleteUseCase userDeleteUseCase) {
        this.userMapper = userMapper;
        this.userCreateUseCase = userCreateUseCase;
        this.userDeleteUseCase = userDeleteUseCase;
    }

    public UserCreateResponse create(@Valid UserCreateRequest userCreateRequest) {
        var user = userCreateUseCase.execute(userCreateRequest);
        return userMapper.toCreateResponse(user);
    }

    public void delete(@Valid UserDeleteRequest userDeleteRequest, long idUserAutenticated) {
        userDeleteUseCase.execute(userDeleteRequest, idUserAutenticated);
    }

}
