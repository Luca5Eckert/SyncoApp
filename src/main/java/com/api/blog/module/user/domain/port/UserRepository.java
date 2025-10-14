package com.api.blog.module.user.domain.port;

import com.api.blog.module.user.domain.UserEntity;

public interface UserRepository {
    void save(UserEntity user);
}
