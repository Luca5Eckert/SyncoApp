package com.api.blog.module.authentication.domain.use_case;

import com.api.blog.module.authentication.application.dto.reset_password.UserResetRequest;
import com.api.blog.module.authentication.domain.exception.password.PasswordNotMatchesException;
import com.api.blog.module.user.domain.exception.UserNotFoundException;
import com.api.blog.module.user.domain.exception.password.PasswordNotValidException;
import com.api.blog.module.user.domain.port.UserRepository;
import com.api.blog.module.user.domain.validator.PasswordValidatorImpl;
import org.passay.PasswordValidator;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserResetPasswordUseCase {

    private final UserRepository userRepository;
    private final PasswordValidatorImpl passwordValidator;
    private final PasswordEncoder passwordEncoder;

    public UserResetPasswordUseCase(UserRepository userRepository, PasswordValidatorImpl passwordValidator, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordValidator = passwordValidator;
        this.passwordEncoder = passwordEncoder;
    }

    public void execute(UserResetRequest userResetRequest, long idUser) {
        var userDetails = userRepository.findById(idUser).orElseThrow( () -> new UserNotFoundException(idUser) );

        if(!passwordEncoder.matches(userResetRequest.passwordActual(), userDetails.getPassword())){
           throw new PasswordNotMatchesException();
        }

        if(!passwordValidator.isValid(userResetRequest.newPassword())) throw new PasswordNotValidException();

        userDetails.setPassword(userResetRequest.newPassword());
        userRepository.save(userDetails);
    }

}
