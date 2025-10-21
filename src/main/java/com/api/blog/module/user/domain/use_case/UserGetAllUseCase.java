package com.api.blog.module.user.domain.use_case;

import com.api.blog.module.user.domain.UserEntity;
import com.api.blog.module.user.domain.enumerator.RoleUser;
import com.api.blog.module.user.domain.filter.PageUser;
import com.api.blog.module.user.domain.filter.PagenableUserProvider;
import com.api.blog.module.user.domain.filter.UserFilter;
import com.api.blog.module.user.domain.filter.UserSearchProvider;
import com.api.blog.module.user.domain.port.UserRepository;
import org.hibernate.query.Page;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

@Component
public class UserGetAllUseCase {

    private final UserRepository userRepository;
    private final UserSearchProvider userCreateSearch;

    public UserGetAllUseCase(UserRepository userRepository, UserSearchProvider userCreateSearch) {
        this.userRepository = userRepository;
        this.userCreateSearch = userCreateSearch;
    }

    public List<UserEntity> execute(String name
            , String email
            , RoleUser roleUser
            , Instant createAt
            , Instant updateAt
            , int pageNumber
            , int pageSize) {

        var criteria = UserFilter.builder()
                .setNameContains(name)
                .setEmailContains(email)
                .setRole(roleUser)
                .setCreatedFrom(createAt)
                .setCreatedTo(updateAt)
                .build();

        var searchSpecification = userCreateSearch.create(criteria);

        var searchPaginable = PagenableUserProvider.toInstance(pageNumber, pageSize);

        return userRepository.findAll(searchSpecification, searchPaginable);
    }
}
