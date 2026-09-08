package com.notifications.service.impl;

import com.notifications.dto.request.EmailRequest;
import com.notifications.dto.response.EmailResponse;
import com.notifications.exception.exceptionClasses.MailSendingException;
import com.notifications.exception.exceptionClasses.SenderMailIdException;
import com.notifications.mapper.EmailDataMapper;
import com.notifications.repository.EmailRepository;
import com.notifications.service.EmailNotificationService;
import com.notifications.template.OtpVerificationEmailTemplate;
import com.notifications.template.PasswordVerificationEmailTemplate;
import com.notifications.template.RegistrationEmailTemplate;
import jakarta.mail.internet.MimeMessage;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailNotificationServiceImpl implements EmailNotificationService {

    private final Environment environment;
    private final JavaMailSender mailer;
    EmailResponse response=new EmailResponse();
    EmailRepository repository;
    String senderMailId;
    String emailContent;

    public EmailNotificationServiceImpl(Environment environment, JavaMailSender mailer,
                                        EmailRepository repository){
        this.environment=environment;
        this.mailer=mailer;
        this.repository = repository;
    }

    @Override
    public EmailResponse sendEmail(EmailRequest request){
        try{
            senderMailId=environment.getProperty("sender-email");
            MimeMessage message=mailer.createMimeMessage();
            MimeMessageHelper messageHelper=new MimeMessageHelper(message);
            System.out.println(request.getEmailId());
            messageHelper.setTo(request.getEmailId());
            //assert senderMailId != null;This will throw assertion error in case of null, but in a
            // production ready system:
            // 1.Validate input properly
            // 2.Throw meaningful exceptions
            // 3.Return proper HTTP error responses
            if (senderMailId == null)
                throw new MailSendingException("Sender mailId can't be empty!...");
            else if(request.getEmailId() == null)
                throw new MailSendingException("Receiver mailId can't be empty!...");

            if(request.getEmailType().equalsIgnoreCase("REG")){
                emailContent = RegistrationEmailTemplate.generateRegistrationEmailTemplate(
                        request.getUserName(), request.getEmailId(), request.getVerificationUrl(),
                        request.getTokenDuration());

            }else if(request.getEmailType().equalsIgnoreCase("PASS-RESET")){
                emailContent = PasswordVerificationEmailTemplate.generatePasswordResetEmailTemplate(
                        request.getVerificationUrl(), request.getTokenDuration());
            }else if(request.getEmailType().equalsIgnoreCase("OTP_VERIFICATION")){
                emailContent = OtpVerificationEmailTemplate.getOtpVerificationMailTemplate(
                        request.getEmailToken());
            }

            messageHelper.setFrom(senderMailId);
            messageHelper.setSubject(request.getEmailSubject());
            messageHelper.setText(emailContent, true);
            mailer.send(message);

            response.setStatusCode(HttpStatus.OK.toString());
            response.setEmailStatus("SUCCESS");
            response.setEmailMessage("Registration Email send successfully!...");
        }catch (Exception exception){
            response.setStatusCode(HttpStatus.OK.toString());
            response.setEmailStatus("FAILED");
            response.setEmailMessage("[Registration Email sending unsuccessful];{exception.getMessage()}");
            throw new SenderMailIdException("[EmailNotificationService]-{Ex}:- "+exception.getMessage());
        }finally {
            repository.save(EmailDataMapper.mapToEmailEntity(request, senderMailId, emailContent,
                    response.getEmailStatus(), response.getEmailMessage()));
        }
        return response;
    }
}
