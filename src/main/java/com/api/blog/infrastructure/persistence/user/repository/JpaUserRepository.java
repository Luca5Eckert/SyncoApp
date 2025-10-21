package com.api.blog.infrastructure.persistence.user.repository;

import com.api.blog.module.user.domain.UserEntity;
import com.api.blog.module.user.domain.vo.Email;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaUserRepository extends JpaRepository<UserEntity, Long>, JpaSpecificationExecutor<User> {
    boolean existsByEmail(Email email);

    Optional<UserEntity> findByEmail(Email email);
}
