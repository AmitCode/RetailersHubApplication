package com.intoThe.dto.request;

import com.intoThe.validation.annoation.AtLeastOneRequired;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserLoginRequest {
    @NotBlank(message = "User Name can't be blank")
    private String userName;
    @NotBlank(message = "Password can't be blank.")
    private String password;
}
