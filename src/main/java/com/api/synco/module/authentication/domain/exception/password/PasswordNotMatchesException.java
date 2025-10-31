package com.api.synco.module.authentication.domain.exception.password;

import com.api.synco.module.authentication.domain.exception.AuthenticationException;

public class PasswordNotMatchesException extends AuthenticationException {

    public PasswordNotMatchesException() {
        super("The password is incorrect");
    }
}
