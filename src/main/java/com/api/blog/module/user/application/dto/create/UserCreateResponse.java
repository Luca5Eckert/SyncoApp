package com.api.blog.module.user.application.dto.create;

import com.api.blog.module.user.domain.enumerator.RoleUser;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserCreateResponse(long id, @NotBlank @Size(max = 30) String name, @NotBlank @Size(max = 150) String email, @NotBlank RoleUser roleUser) {
}
