package com.api.synco.module.user.domain.exception.permission;

import com.api.synco.module.user.domain.exception.UserException;

public class UserWithoutDeletePermissionException extends UserException {
    public UserWithoutDeletePermissionException(String message) {
        super(message);
    }

    public UserWithoutDeletePermissionException() {
        super("User don't have the permission to delete another user");
    }

}
