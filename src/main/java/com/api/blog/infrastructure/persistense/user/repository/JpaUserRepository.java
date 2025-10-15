package com.api.blog.infrastructure.persistense.user.repository;

import com.api.blog.module.user.domain.UserEntity;
import com.api.blog.module.user.domain.vo.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByEmail(Email email);

    Optional<UserEntity> findByEmail(Email email);
}
