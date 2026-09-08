package com.notifications.service;

import com.notifications.dto.request.EmailRequest;
import com.notifications.dto.response.EmailResponse;
import jakarta.mail.MessagingException;

public interface EmailNotificationService {
    public EmailResponse sendEmail(EmailRequest request);
}
