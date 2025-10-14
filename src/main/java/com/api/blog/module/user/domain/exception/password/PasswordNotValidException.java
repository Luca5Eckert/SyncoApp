package com.api.blog.module.user.domain.exception.password;

import com.api.blog.module.user.domain.UserEntity;
import com.api.blog.module.user.domain.exception.UserException;

public class PasswordNotValidException extends UserException {
    public PasswordNotValidException(String message) {
        super(message);
    }

    public PasswordNotValidException() {
        super("The password is not valid");
    }
}
