package com.intoThe.service.client;

import com.intoThe.dto.request.EmailRequest;
import com.intoThe.dto.response.EmailResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("notification-application")
public interface NotificationServiceClient {

    @PostMapping(value = "email/sendMail", consumes = "application/json")
    public ResponseEntity<EmailResponse> sendEmail(@RequestBody EmailRequest request);
}
