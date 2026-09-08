package com.user.service.app.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.servers.ServerVariable;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "User Service API",
                version = "UserDev1.0",
                summary = "APIs for user registration, activation, and profile management",
                description = """
                        Provides internal REST APIs for managing user accounts and user-related
                        information after successful registration and email verification.
                        
                        The Auth Service handles user registration, email verification, and
                        authentication. Once a user's verification link is successfully validated,
                        the Auth Service communicates with this service to create, activate, or
                        update the corresponding user information.
                        
                        The service currently supports synchronous inter-service communication
                        and is designed to support asynchronous communication in the future.
                        """
        ),

        tags = {
                @Tag(
                        name = "User Management APIs",
                        description = "APIs for creating, activating, updating, and managing user information"
                )
        }
)
public class OpenApiDocConfiguration {
}
