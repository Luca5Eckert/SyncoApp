package com.api.synco.infrastructure.security.user_details;

import com.api.synco.module.user.domain.UserEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsMapper {

    public UserDetails toEntity(UserEntity user) {
        return new UserDetailsImpl(
                user.getId()
                , user.getEmail().address()
                , user.getPassword()
                , user.getRole());
    }

}
