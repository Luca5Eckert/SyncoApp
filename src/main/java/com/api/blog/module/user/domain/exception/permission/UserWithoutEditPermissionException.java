package com.api.blog.module.user.domain.exception.permission;

import com.api.blog.module.user.domain.exception.UserException;

public class UserWithoutEditPermissionException extends UserException {
    public UserWithoutEditPermissionException(String message) {
        super(message);
    }

    public UserWithoutEditPermissionException() {
      super("User don't have the permission to edit another user");
    }
}
