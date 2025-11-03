package com.api.synco.module.user.domain.exception.permission;

import com.api.synco.module.user.domain.exception.UserDomainException;

public class UserWithoutEditPermissionDomainException extends UserDomainException {
    public UserWithoutEditPermissionDomainException(String message) {
        super(message);
    }

    public UserWithoutEditPermissionDomainException() {
      super("User don't have the permission to edit another user");
    }
}
