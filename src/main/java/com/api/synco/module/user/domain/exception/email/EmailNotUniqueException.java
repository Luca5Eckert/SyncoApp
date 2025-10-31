package com.api.synco.module.user.domain.exception.email;

import com.api.synco.module.user.domain.exception.UserException;

public class EmailNotUniqueException extends UserException {
    public EmailNotUniqueException(String message) {
        super(message);
    }

    public EmailNotUniqueException() {
        super("The email is not unique");
    }

}
