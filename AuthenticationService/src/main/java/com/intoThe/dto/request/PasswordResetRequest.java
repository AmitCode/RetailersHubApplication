package com.intoThe.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Schema(
        name = "PasswordResetRequest",
        description = "Request payload for resetting a user's password."
)
public class PasswordResetRequest {

    @Schema(
            description = "Password reset token received through the password reset link.",
            example = "eyJhbGciOiJIUzI1NiJ9...",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Password reset token is required.")
    private String token;

    @Schema(
            description = "Current password of the user.",
            example = "OldPassword@123",
            format = "password",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Current password is required.")
    private String oldPassword;

    @Schema(
            description = "New password to be set for the user account.",
            example = "NewPassword@123",
            format = "password",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "New password is required.")
    private String newPassword;
}