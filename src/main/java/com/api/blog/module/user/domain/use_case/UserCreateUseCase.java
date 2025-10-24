package com.api.blog.module.user.domain.use_case;

import com.api.blog.module.user.application.dto.create.UserCreateRequest;
import com.api.blog.module.user.domain.UserEntity;
import com.api.blog.module.user.domain.exception.email.EmailNotUniqueException;
import com.api.blog.module.user.domain.exception.password.PasswordNotValidException;
import com.api.blog.module.user.domain.port.UserRepository;
import com.api.blog.module.user.domain.validator.PasswordValidatorImpl;
import com.api.blog.module.user.domain.vo.Email;
import com.api.blog.module.user.domain.vo.Name;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserCreateUseCase {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final PasswordValidatorImpl passwordValidator;

    public UserCreateUseCase(UserRepository userRepository, PasswordEncoder passwordEncoder, PasswordValidatorImpl passwordValidator) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.passwordValidator = passwordValidator;
    }

    /**
     * Method responsible for executing the use case.
     *
     * <p>This method will validate the name, email, and password. After validation,
     * the user will be saved in the database.</p>
     *
     * @param userCreateRequest Request with user data.
     * @return The created user.
     */
    public UserEntity execute(UserCreateRequest userCreateRequest) {
        log.info("User creation attempt for email: {} with role: {}", userCreateRequest.email(), userCreateRequest.roleUser());

        Name name = new Name(userCreateRequest.name());
        Email email = new Email(userCreateRequest.email());

        if(!passwordValidator.isValid(userCreateRequest.password())) {
            log.warn("User creation failed - invalid password for email: {}", userCreateRequest.email());
            throw new PasswordNotValidException();
        }

        String password = passwordEncoder.encode(userCreateRequest.password());

        UserEntity user = new UserEntity(name, email, password, userCreateRequest.roleUser());

        if(userRepository.existsByEmail(user.getEmail())) {
            log.warn("User creation failed - email already exists: {}", userCreateRequest.email());
            throw new EmailNotUniqueException();
        }

        userRepository.save(user);
        
        log.info("User created successfully: {} with role: {}", user.getEmail(), user.getRoleUser());

        return user;
    }


}
