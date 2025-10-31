package com.api.synco.module.user.application.dto.edit;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserEditResponse(long id, @NotBlank @Size(max = 30) String name, @NotBlank @Size(max = 150) String email) {
}
