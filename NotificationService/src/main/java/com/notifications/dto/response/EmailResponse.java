package com.notifications.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Schema(description = "Response returned after an email delivery request")
public class EmailResponse {

    @Schema(
            description = "Application-specific status code indicating the result of the email operation",
            example = "200"
    )
    private String statusCode;

    @Schema(
            description = "Status of the email operation",
            example = "SUCCESS"
    )
    private String emailStatus;

    @Schema(
            description = "Message describing the result of the email operation",
            example = "Email sent successfully"
    )
    private String emailMessage;
}