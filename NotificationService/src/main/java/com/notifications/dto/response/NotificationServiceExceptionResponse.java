package com.notifications.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Schema(description = "Response returned when an error occurs in the Notification Service")
public class NotificationServiceExceptionResponse {

    @Schema(
            description = "HTTP status code associated with the error",
            example = "400"
    )
    private int statusCode;

    @Schema(
            description = "Description of the error",
            example = "Invalid email address"
    )
    private String statusMsg;

    @Schema(
            description = "Date and time when the error occurred",
            example = "2026-08-26T14:30:45"
    )
    private LocalDateTime errorTimeStamp;

    @Schema(
            description = "API endpoint where the error occurred",
            example = "/api/notifications/emails"
    )
    private String path;
}
