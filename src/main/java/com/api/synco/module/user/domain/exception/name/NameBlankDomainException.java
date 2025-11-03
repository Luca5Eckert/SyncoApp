package com.api.synco.module.user.domain.exception.name;

import com.api.synco.module.user.domain.exception.UserDomainException;

public class NameBlankDomainException extends UserDomainException {
    public NameBlankDomainException(String message) {
        super(message);
    }

    public NameBlankDomainException() {
      super("The name can't be blank");
    }

}
