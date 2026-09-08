package com.user.service.app.services;

import com.user.service.app.dtos.request.EmailRequest;
import com.user.service.app.dtos.response.EmailServiceResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;

public class WebClientService {
    public static ResponseEntity<EmailServiceResponse> callNotificationServiceEmail(EmailRequest request,
                                                                                    WebClient client){
        return client.post()
                .uri("email/sendMail")
                .bodyValue(request)
                .retrieve()
                .toEntity(EmailServiceResponse.class)
                .block();
    }
}
