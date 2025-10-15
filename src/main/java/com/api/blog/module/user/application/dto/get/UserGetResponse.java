package com.api.blog.module.user.application.dto.get;

import com.api.blog.module.user.domain.enumerator.RoleUser;

import java.time.Instant;

public record UserGetResponse(long id, String name, String email, RoleUser role, Instant createAt, Instant updateAt) {
}
