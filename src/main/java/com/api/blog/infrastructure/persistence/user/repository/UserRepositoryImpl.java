package com.api.blog.infrastructure.persistence.user.repository;

import com.api.blog.module.user.domain.UserEntity;
import com.api.blog.module.user.domain.port.UserRepository;
import com.api.blog.module.user.domain.vo.Email;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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

    @Override
    public boolean existsByEmail(Email email) {
        return jpaUserRepository.existsByEmail(email);
    }

    @Override
    public Optional<UserEntity> findById(long idUserAutenticated) {
        return jpaUserRepository.findById(idUserAutenticated);
    }

    @Override
    public boolean existsById(long id) {
        return jpaUserRepository.existsById(id);
    }

    @Override
    public void deleteById(long id) {
        jpaUserRepository.deleteById(id);
    }

    @Override
    public List<UserEntity> findAll() {
        return jpaUserRepository.findAll();
    }

    @Override
    public Optional<UserEntity> findByEmail(Email email) {
        return jpaUserRepository.findByEmail(email);
    }

}
