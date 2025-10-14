package com.api.blog.module.user.domain.service;

import com.api.blog.module.user.application.dto.create.UserCreateRequest;
import com.api.blog.module.user.application.dto.create.UserCreateResponse;
import com.api.blog.module.user.domain.mapper.UserMapper;
import com.api.blog.module.user.domain.use_case.UserCreateUseCase;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserMapper userMapper;

    private final UserCreateUseCase userCreateUseCase;

    public UserService(UserMapper userMapper, UserCreateUseCase userCreateUseCase) {
        this.userMapper = userMapper;
        this.userCreateUseCase = userCreateUseCase;
    }

    public UserCreateResponse create(@Valid UserCreateRequest userCreateRequest) {
        var user = userCreateUseCase.execute(userCreateRequest);
        return userMapper.toCreateResponse(user);
    }
}
