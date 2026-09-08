package com.user.service.app.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserDto {
    private Long userId;
    @NotBlank(message = "Auth Service userId can't be blank")
    private Long authUserId;
    @NotBlank(message = "User Name can't be blank")
    private String userName;
    @NotBlank(message = "User First Name is required")
    private String userFirstName;

    @NotBlank(message = "User Middle Name is required")
    private String userMiddleName;

    @NotBlank(message = "User Last Name is required")
    private String userLastName;

    @NotBlank(message = "User Contact Number is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Contact number must be 10 digits")
    private String userContactNumber;

    @NotBlank(message = "User Email is required")
    @Email(message = "Email must be in proper format")
    private String userEmailId;
    private Boolean isUserActive;
    private Boolean isMobileVerified;
    private Boolean isEmailVerified;
    @NotBlank(message = "User Password can't be empty!...")
    private String userPassword;
    @NotBlank(message = "Re-confirm your password!...")
    private String confirmPassword;
    private List<UserAddressDto> addresses;

}
