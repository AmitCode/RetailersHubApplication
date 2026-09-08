package com.notifications.controller;

import com.notifications.dto.request.EmailRequest;
import com.notifications.dto.response.EmailResponse;
import com.notifications.service.EmailNotificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
@Tag(name = "Notification APIs")
public class EmailController {
    private final EmailNotificationService notificationService;
    public EmailController(EmailNotificationService service){
        this.notificationService=service;
    }

    @Operation(
            summary = "Send an email",
            description = """
                Sends an email to the specified recipient based on the requested email type.
                Currently supports OTP emails and verification-link emails.
                """
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Email sent successfully",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = EmailResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid email request",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Failed to send email",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE
                    )
            )
    })
    @PostMapping("/sendMail")
    public ResponseEntity<EmailResponse> sendEmail(@RequestBody EmailRequest request){
        return new ResponseEntity<>(notificationService.sendEmail(request), HttpStatus.OK);
    }
}
