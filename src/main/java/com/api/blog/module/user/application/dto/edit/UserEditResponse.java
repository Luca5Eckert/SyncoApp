package com.api.blog.module.user.application.dto.edit;

import com.api.blog.module.user.domain.enumerator.RoleUser;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserEditResponse(long id, @NotBlank @Size(max = 30) String name, @NotBlank @Size(max = 150) String email) {
}
