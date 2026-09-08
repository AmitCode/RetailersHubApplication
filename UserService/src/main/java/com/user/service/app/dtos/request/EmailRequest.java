package com.user.service.app.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmailRequest {
    private String userName;
    private String emailId;
    private String emailType;
    private String emailSubject;
    private String verificationUrl;
}
