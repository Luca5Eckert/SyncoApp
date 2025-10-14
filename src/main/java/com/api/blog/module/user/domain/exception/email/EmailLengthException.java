package com.api.blog.module.user.domain.exception.email;

import com.api.blog.module.user.domain.exception.UserException;

public class EmailLengthException extends UserException {

    public EmailLengthException(String message) {
        super(message);
    }

    public EmailLengthException(int length) {
        super("The email length is invalid. The maximum caracters is " + length);
    }

}
