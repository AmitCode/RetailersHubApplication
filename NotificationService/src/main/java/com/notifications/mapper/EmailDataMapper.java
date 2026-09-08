package com.notifications.mapper;

import com.notifications.dto.request.EmailRequest;
import com.notifications.entities.Emails;

public class EmailDataMapper {
    public static Emails mapToEmailEntity(EmailRequest emailRequest, String senderEmailId, String emailContent,
                                          String status, String statusMessage){
        Emails email = new Emails();
        email.setEmailBody(emailContent)
             .setRecipientEmailId(emailRequest.getEmailId())
             .setEmailSubject(emailRequest.getEmailSubject())
             .setSenderMailId(senderEmailId)
             .setVerificationUrl(emailRequest.getVerificationUrl())
             .setStatus(status)
             .setStatusMessage(statusMessage);
        return email;
    }
}
