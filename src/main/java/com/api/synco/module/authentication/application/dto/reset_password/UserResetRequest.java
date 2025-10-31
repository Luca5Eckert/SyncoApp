package com.api.synco.module.authentication.application.dto.reset_password;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserResetRequest(
        @NotBlank(message = "The actual password can't be blank") @Size(max = 180, message = "The password can't be more then 180 characters") String passwordActual,
        @NotBlank(message = "The new password can't be blank") @Size(max = 180, message = "The password can't be more then 180 characters") String newPassword
) {

}
