package com.intoThe.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Schema(
        name = "UserUpdateRequest",
        description = "Request payload for updating user details."
)
public class UserUpdateRequest {

    @Schema(
            description = "Username of the user.",
            example = "AmanPandey1216"
    )
    private String userName;

    @Schema(
            description = "Email address of the user.",
            example = "user@example.com"
    )
    private String userEmail;
}
