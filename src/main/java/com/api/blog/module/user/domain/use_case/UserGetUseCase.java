package com.api.blog.module.user.domain.use_case;

import com.api.blog.module.user.domain.UserEntity;
import com.api.blog.module.user.domain.exception.UserNotFoundException;
import com.api.blog.module.user.domain.port.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class UserGetUseCase {

    private final UserRepository userRepository;

    public UserGetUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity execute(long id) {
        return userRepository.findById(id).orElseThrow( () -> new UserNotFoundException(id) );
    }

}
