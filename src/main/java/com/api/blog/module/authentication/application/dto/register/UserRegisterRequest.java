package com.api.blog.module.authentication.application.dto.register;

import com.api.blog.module.user.domain.enumerator.RoleUser;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserRegisterRequest(@NotBlank @Size(max = 30) String name, @NotBlank @Size(max = 150) String email, @NotBlank @Size(max = 180)  String password) {
}
