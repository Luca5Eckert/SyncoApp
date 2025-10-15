package com.api.blog.module.user.domain.use_case;

import com.api.blog.module.user.domain.UserEntity;
import com.api.blog.module.user.domain.port.UserRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserGetAllUseCase {

    private final UserRepository userRepository;

    public UserGetAllUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserEntity> execute() {
        return userRepository.findAll();
    }

}
