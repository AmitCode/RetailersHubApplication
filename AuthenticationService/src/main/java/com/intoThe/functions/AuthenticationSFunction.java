package com.intoThe.functions;

import com.intoThe.service.UserService;
import io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;

public class AuthenticationSFunction {

    private static final Logger log = LoggerFactory.getLogger(AuthenticationSFunction.class);

    @Bean
    public MessagePassingQueue.Consumer<String> updateCommunication(UserService userService) {
        return userName -> {
            log.info("Updating Communication status for the user name : " + userName);
            userService.updateCommunicationStatus(userName);
        } ;
    }
}
