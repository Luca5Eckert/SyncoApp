package com.api.synco.module.user.domain.exception.permission;

import com.api.synco.module.user.domain.exception.UserDomainException;

public class UserWithoutDeletePermissionDomainException extends UserDomainException {
    public UserWithoutDeletePermissionDomainException(String message) {
        super(message);
    }

    public UserWithoutDeletePermissionDomainException() {
        super("User don't have the permission to delete another user");
    }

}
