package com.api.synco.module.user.domain.exception.name;

import com.api.synco.module.user.domain.exception.UserException;

public class NameBlankException extends UserException {
    public NameBlankException(String message) {
        super(message);
    }

    public NameBlankException() {
      super("The name can't be blank");
    }

}
