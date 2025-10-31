package com.api.synco.module.user.domain.exception.email;

import com.api.synco.module.user.domain.exception.UserException;

public class EmailBlankException extends UserException {
    public EmailBlankException(String message) {
        super(message);
    }

    public EmailBlankException() {
      super("The email can't be blank");
    }
}
