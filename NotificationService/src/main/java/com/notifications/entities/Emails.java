package com.notifications.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "INTO_THE_EMAILS")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Emails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long emailTransId;
    private String senderMailId;
    private String recipientEmailId;
    private String emailSubject;
    private String verificationUrl;
    @Column(length = 2000)
    private String emailBody;
    private String status;
    private String statusMessage;
    @CreationTimestamp
    private String emailDate;

    public Emails setEmailTransId(long emailTransId) {
        this.emailTransId = emailTransId;
        return this;
    }

    public Emails setSenderMailId(String senderMailId) {
        this.senderMailId = senderMailId;
        return this;
    }

    public Emails setRecipientEmailId(String recipientEmailId) {
        this.recipientEmailId = recipientEmailId;
        return this;
    }

    public Emails setEmailSubject(String emailSubject) {
        this.emailSubject = emailSubject;
        return this;
    }

    public Emails setVerificationUrl(String verificationUrl) {
        this.verificationUrl = verificationUrl;
        return this;
    }

    public Emails setEmailBody(String emailBody) {
        this.emailBody = emailBody;
        return this;
    }

    public Emails setStatus(String status) {
        this.status = status;
        return this;
    }

    public Emails setEmailDate(String emailDate) {
        this.emailDate = emailDate;
        return this;
    }

    public Emails setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
        return this;
    }


}
