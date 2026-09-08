package com.user.service.app.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class MainApplicationConfiguration {
    @Value("${notificationServiceBaseUrl}")
    private String baseUrl;
    @Bean
    public WebClient getWebClient(){
        return WebClient.builder().baseUrl(baseUrl).build();
    }
}
