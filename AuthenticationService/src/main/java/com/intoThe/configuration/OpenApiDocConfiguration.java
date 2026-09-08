package com.intoThe.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.servers.ServerVariable;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Authentication Service API",
                version = "AuthDev1.0",
                summary = "Authentication, initial user, and role management APIs",
                description = """
                Provides REST APIs for user authentication, initial user registration and profile management,
                and role management. The service supports user authentication, JWT access token generation
                and refresh, and authentication-protected operations requiring a valid JWT Bearer token.
                """
        ),
        servers = @Server(
//                url = "https://{environment}.example.com/authService",
                url = "${notificationServiceBaseUrl}" + "${server.servlet.context-path}",
                description = "Authentication Service",
                variables = {
                        @ServerVariable(
                                name = "Environment",
                                defaultValue = "DEV",
                                allowableValues = {"LOCAL", "DEV", "QA", "PROD"}
                        )
                }
        ),

        tags = {
                @Tag(
                        name = "Authentication Management APIs",
                        description = "APIs for managing user authentication"
                ),
                @Tag(
                        name = "User Management APIs",
                        description = "APIs for managing users"
                ),
                @Tag(
                        name = "Token Management APIs",
                        description = "APIs for managing tokens"
                ),
                @Tag(
                        name = "Otp Management APIs",
                        description = "APIs for managing otp"
                )
        }
)
public class OpenApiDocConfiguration {
}
