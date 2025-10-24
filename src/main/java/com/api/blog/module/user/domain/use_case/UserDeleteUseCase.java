package com.api.blog.module.user.domain.use_case;

import com.api.blog.module.user.application.dto.delete.UserDeleteRequest;
import com.api.blog.module.user.domain.exception.UserNotFoundException;
import com.api.blog.module.user.domain.exception.permission.UserWithoutDeletePermissionException;
import com.api.blog.module.user.domain.port.UserRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserDeleteUseCase {

    private final UserRepository userRepository;

    public UserDeleteUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void execute(@Valid UserDeleteRequest userDeleteRequest, long idUserAutenticated) {
        log.info("User deletion attempt - User ID: {} being deleted by authenticated user ID: {}", userDeleteRequest.id(), idUserAutenticated);

        if(!userRepository.existsById(userDeleteRequest.id())) {
            log.warn("User deletion failed - User not found with ID: {}", userDeleteRequest.id());
            throw new UserNotFoundException(userDeleteRequest.id());
        }

        var userAuthenticated = userRepository.findById(idUserAutenticated).orElseThrow(() -> new UserNotFoundException(idUserAutenticated));

        if(!userAuthenticated.canDeleteUser()) {
            log.warn("User deletion failed - User ID: {} does not have permission to delete users", idUserAutenticated);
            throw new UserWithoutDeletePermissionException();
        }

        userRepository.deleteById(userDeleteRequest.id());
        
        log.info("User deleted successfully - User ID: {} deleted by user ID: {}", userDeleteRequest.id(), idUserAutenticated);

    }

}
