package com.api.synco.module.user.domain.exception.name;

import com.api.synco.module.user.domain.exception.UserDomainException;

public class NameLengthDomainException extends UserDomainException {
    public NameLengthDomainException(String message) {
        super(message);
    }

    public NameLengthDomainException(int length){
      super("The name length is invalid. The maximum caracters is " + length);
    }

}
