package com.intoThe.service.client;

import com.intoThe.dto.request.EmailRequest;
import com.intoThe.dto.response.EmailResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class NotificationFallback implements NotificationServiceClient{

    @Override
    public ResponseEntity<EmailResponse> sendEmail(EmailRequest request) {
        return null;
    }
}
