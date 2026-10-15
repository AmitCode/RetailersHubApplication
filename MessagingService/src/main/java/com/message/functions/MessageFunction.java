package com.message.functions;

import com.message.dto.AuthenticationSMessageDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;


@Configuration
public class MessageFunction {

    private static final Logger logger = LoggerFactory.getLogger(MessageFunction.class);

    @Bean
    public Function<AuthenticationSMessageDto, AuthenticationSMessageDto> email() {
        return accountsMsgDto -> {
            logger.info("Sending email with the details : " +  accountsMsgDto.toString());
            return accountsMsgDto;
        };
    }

    @Bean
    public Function<AuthenticationSMessageDto, String> sms() {
        return accountsMsgDto -> {
            logger.info("Sending sms with the details : " +  accountsMsgDto.toString());
            return accountsMsgDto.userName();
        };
    }
}
