package com.notifications.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Schema(description = "Request payload for sending an email")
public class EmailRequest {

    @Schema(
            description = "Name of the user receiving the email",
            example = "Amit Kumar Pandey"
    )
    private String userName;

    @Schema(
            description = "Email address of the recipient",
            example = "amit@example.com"
    )
    private String emailId;

    @Schema(
            description = "Type of email to be sent",
            example = "OTP"
    )
    private String emailType;

    @Schema(
            description = "Subject of the email",
            example = "Your OTP for account verification"
    )
    private String emailSubject;

    @Schema(
            description = "Verification URL included in verification emails",
            example = "https://example.com/verify?token=abc123"
    )
    private String verificationUrl;

    @Schema(
            description = "Duration for which the OTP or verification token remains valid",
            example = "10 minutes"
    )
    private String tokenDuration;

    @Schema(
            description = "OTP or email verification token",
            example = "583921"
    )
    private String emailToken;
}
