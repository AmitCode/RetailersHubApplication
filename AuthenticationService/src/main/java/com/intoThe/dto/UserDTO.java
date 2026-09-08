package com.intoThe.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Schema(
        name = "UserRegistrationRequest",
        description = "Request payload for registering a new user."
)
public class UserDTO {
    private Long userId;
    @NotBlank(message = "User Name can't be empty!...")
    private String userName;
    @NotBlank(message = "User Email can't be null")
    @Email(message = "Email must be in proper format!...")
    private String userEmail;
    private Boolean isUserActive;
    @NotBlank(message = "User Password can't be empty!...")
    private String userPassword;

}
