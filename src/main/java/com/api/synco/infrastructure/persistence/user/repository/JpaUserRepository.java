package com.api.synco.infrastructure.persistence.user.repository;

import com.api.synco.module.user.domain.UserEntity;
import com.api.synco.module.user.domain.vo.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaUserRepository extends JpaRepository<UserEntity, Long>, JpaSpecificationExecutor<UserEntity> {
    boolean existsByEmail(Email email);

    Optional<UserEntity> findByEmail(Email email);
}
