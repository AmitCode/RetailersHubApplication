package com.intoThe.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Schema(
        name = "UserRegistrationRequest",
        description = "Request payload for registering a new user."
)
public class UserRegistrationRequest {

    @Schema(
            description = "Username of the user.",
            example = "AmanKrPandey1216",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "User Name can't be empty!...")
    private String userName;

    @Schema(
            description = "Email address used for registration and account verification.",
            example = "user@example.com",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "User Email can't be null")
    @Email(message = "Email must be in proper format!...")
    private String userEmail;

    @Schema(
            description = "Password used to authenticate the user.",
            example = "StrongPassword@123",
            requiredMode = Schema.RequiredMode.REQUIRED,
            format = "password"
    )
    @NotBlank(message = "User Password can't be empty!...")
    private String userPassword;
}
