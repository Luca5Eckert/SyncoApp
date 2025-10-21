package com.api.blog.infrastructure.persistence.user.repository;

import com.api.blog.module.user.domain.UserEntity;
import com.api.blog.module.user.domain.enumerator.RoleUser;
import com.api.blog.module.user.domain.port.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class UserRepositoryImplTest {

    @Autowired
    private UserRepository userRepository;

    private long id;
    private UserEntity user;

    @BeforeEach
    public void setup(){
        id = 1L;
        user = new UserEntity(id, null, null, null, RoleUser.ADMIN);
    }

    @Test
    void shouldSave() {
    }

    @Test
    void shouldReturnTrueWhenExistsByEmail() {
    }

    @Test
    void shouldReturnUserWhenFoundById() {
    }

    @Test
    void shouldReturnTrueWhenExistsById() {
    }

    @Test
    void shouldDeleteWhenFoundById() {
    }

    @Test
    void shouldReturnAllUsersWhenFound() {
    }

    @Test
    void shouldReturnUserWhenFoundByEmail() {
    }

}