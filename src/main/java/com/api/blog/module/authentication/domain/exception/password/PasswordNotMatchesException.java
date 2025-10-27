package com.api.blog.module.authentication.domain.exception.password;

import com.api.blog.module.authentication.domain.exception.AuthenticationException;

public class PasswordNotMatchesException extends AuthenticationException {

    public PasswordNotMatchesException() {
        super("The password is incorrect");
    }
}
