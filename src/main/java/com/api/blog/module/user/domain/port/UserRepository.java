package com.api.blog.module.user.domain.port;

import com.api.blog.module.user.domain.UserEntity;
import com.api.blog.module.user.domain.vo.Email;

public interface UserRepository {
    void save(UserEntity user);

    boolean existsByEmail(Email email);
}
