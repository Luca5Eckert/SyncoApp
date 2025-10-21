package com.api.blog.module.user.domain.service;

import com.api.blog.module.user.application.dto.create.UserCreateRequest;
import com.api.blog.module.user.application.dto.create.UserCreateResponse;
import com.api.blog.module.user.application.dto.delete.UserDeleteRequest;
import com.api.blog.module.user.application.dto.edit.UserEditRequest;
import com.api.blog.module.user.application.dto.edit.UserEditResponse;
import com.api.blog.module.user.application.dto.get.UserGetResponse;
import com.api.blog.module.user.domain.enumerator.RoleUser;
import com.api.blog.module.user.domain.filter.UserFilter;
import com.api.blog.module.user.domain.mapper.UserMapper;
import com.api.blog.module.user.domain.use_case.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class UserService {

    private final UserMapper userMapper;

    private final UserCreateUseCase userCreateUseCase;
    private final UserDeleteUseCase userDeleteUseCase;
    private final UserEditUseCase userEditUseCase;
    private final UserGetUseCase userGetUseCase;
    private final UserGetAllUseCase userGetAllUseCase;

    public UserService(UserMapper userMapper, UserCreateUseCase userCreateUseCase, UserDeleteUseCase userDeleteUseCase, UserEditUseCase userEditUseCase, UserGetUseCase userGetUseCase, UserGetAllUseCase userGetAllUseCase) {
        this.userMapper = userMapper;
        this.userCreateUseCase = userCreateUseCase;
        this.userDeleteUseCase = userDeleteUseCase;
        this.userEditUseCase = userEditUseCase;
        this.userGetUseCase = userGetUseCase;
        this.userGetAllUseCase = userGetAllUseCase;
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

    public UserGetResponse get(long id) {
        var user = userGetUseCase.execute(id);
        return userMapper.toGetResponse(user);
    }

    public List<UserGetResponse> getAll(String name, String email, RoleUser roleUser, Instant createAt, Instant updateAt, int pageNumber, int pageSize) {
        var users = userGetAllUseCase.execute(name, email, roleUser, createAt, updateAt, pageNumber, pageSize);
        return users.stream().map(userMapper::toGetResponse).toList();
    }

}
