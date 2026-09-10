package com.notifications.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Notification Service API",
                version = "NotS1.0",
                summary = "APIs for email, OTP, and notification delivery",
                description = "Provides REST APIs for delivering application notifications. The service currently " +
                        "supports email-based OTP delivery and  verification-link emails, with support for " +
                        "additional OTP and notification channels planned for future releases."
        ),
        tags = {
                @Tag(
                        name = "Notification APIs",
                        description = "APIs for sending emails, OTPs, and application notifications"
                )
        }
)
public class NotificationServiceOpenApiConfiguration {
}
