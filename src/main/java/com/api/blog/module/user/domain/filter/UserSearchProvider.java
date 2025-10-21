package com.api.blog.module.user.domain.filter;

import com.api.blog.infrastructure.persistence.user.specification.UserSpecifications;
import com.api.blog.module.user.domain.UserEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class UserSearchProvider {

    public Specification<UserEntity> create(UserFilter userFilter){
        return UserSpecifications.nameContains(userFilter.nameContains())
                .and(UserSpecifications.emailContains(userFilter.emailContains()))
                .and(UserSpecifications.createdBetween(userFilter.createAt(), userFilter.createTo()))
                .and(UserSpecifications.roleEquals(userFilter.role()));
    }

}
