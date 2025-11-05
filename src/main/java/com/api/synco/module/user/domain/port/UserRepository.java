package com.api.synco.module.user.domain.port;

import com.api.synco.module.course.domain.CourseEntity;
import com.api.synco.module.user.domain.UserEntity;
import com.api.synco.module.user.domain.filter.PageUser;
import com.api.synco.module.user.domain.vo.Email;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

public interface UserRepository {
    void save(UserEntity user);

    boolean existsByEmail(Email email);

    Optional<UserEntity> findById(long idUserAutenticated);

    boolean existsById(long id);

    void deleteById(long id);

    Page<UserEntity> findAll(Specification<UserEntity> userEntitySpecification, PageUser pageUser);

    Optional<UserEntity> findByEmail(Email email);
}
