package com.api.blog.infrastructure.security.user_details;

import com.api.blog.module.user.domain.UserEntity;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsMapper {

    public UserDetails toEntity(UserEntity user) {
        return new UserDetailsImpl(
                user.getId()
                , user.getEmail().address()
                , user.getPassword()
                , user.getRole());
    }

}
