package com.api.synco.module.user.domain.use_case;

import com.api.synco.module.user.application.dto.delete.UserDeleteRequest;
import com.api.synco.module.user.domain.exception.UserNotFoundException;
import com.api.synco.module.user.domain.exception.permission.UserWithoutDeletePermissionException;
import com.api.synco.module.user.domain.port.UserRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Component;

@Component
public class UserDeleteUseCase {

    private final UserRepository userRepository;

    public UserDeleteUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void execute(@Valid UserDeleteRequest userDeleteRequest, long idUserAutenticated) {

        if(!userRepository.existsById(userDeleteRequest.id())) throw new UserNotFoundException(userDeleteRequest.id());

        var userAuthenticated = userRepository.findById(idUserAutenticated).orElseThrow(() -> new UserNotFoundException(idUserAutenticated));

        if(!userAuthenticated.canDeleteUser()) throw new UserWithoutDeletePermissionException();

        userRepository.deleteById(userDeleteRequest.id());

    }

}
