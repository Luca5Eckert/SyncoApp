package com.api.blog.infrascturure.persistense.user.repository;

import com.api.blog.module.user.domain.UserEntity;
import com.api.blog.module.user.domain.port.UserRepository;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final JpaUserRepository jpaUserRepository;

    public UserRepositoryImpl(JpaUserRepository jpaUserRepository) {
        this.jpaUserRepository = jpaUserRepository;
    }

    @Override
    public void save(UserEntity user) {
        jpaUserRepository.save(user);
    }

}
