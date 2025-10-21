package com.api.blog.module.user.domain.port;

import com.api.blog.module.user.domain.UserEntity;
import com.api.blog.module.user.domain.vo.Email;
import org.hibernate.query.Page;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    void save(UserEntity user);

    boolean existsByEmail(Email email);

    Optional<UserEntity> findById(long idUserAutenticated);

    boolean existsById(long id);

    void deleteById(long id);

    List<UserEntity> findAll(Specification<UserEntity> userEntitySpecification);

    Optional<UserEntity> findByEmail(Email email);
}
