package com.intoThe.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ApplicationConfiguration {
    @Value("${notificationServiceBaseUrl}")
    private String notificationServiceBaseUrl;

    @Value("${userServiceBaseUrl}")
    private String userServiceBaseUrl;

    @Bean(name = "notificationServiceWebClient")
    public WebClient notificationServiceWebClient(){
        return WebClient.builder().baseUrl(notificationServiceBaseUrl).build();
    }

    @Bean(name = "userServiceWebClient")
    public WebClient userServiceWebClient(){
        return WebClient.builder().baseUrl(userServiceBaseUrl).build();
    }
}
