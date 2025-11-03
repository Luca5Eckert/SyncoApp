package com.api.synco.module.user.domain.exception.email;

import com.api.synco.module.user.domain.exception.UserDomainException;

public class EmailBlankDomainException extends UserDomainException {
    public EmailBlankDomainException(String message) {
        super(message);
    }

    public EmailBlankDomainException() {
      super("The email can't be blank");
    }
}
