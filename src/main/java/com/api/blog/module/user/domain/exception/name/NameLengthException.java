package com.api.blog.module.user.domain.exception.name;

import com.api.blog.module.user.domain.exception.UserException;

public class NameLengthException extends UserException {
    public NameLengthException(String message) {
        super(message);
    }

    public NameLengthException(int length){
      super("The name length is invalid. The maximum caracters is " + length);
    }

}
