package com.intoThe.service;

import com.intoThe.dto.UserDTO;
import com.intoThe.dto.request.EmailRequest;
import com.intoThe.dto.response.AuthenticationServiceResponse;
import com.intoThe.dto.response.EmailServiceResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;

public class WebClientServices {
    public static ResponseEntity<AuthenticationServiceResponse> callUserService(String apiUri,
            UserDTO userDTO, WebClient webClient){

        return webClient.post()
                //.uri("/usersOpr/v1/addNewUser")
                .uri(apiUri)
                .bodyValue(userDTO)
                .retrieve()
                .toEntity(AuthenticationServiceResponse.class)
                .block();
    }

    public static ResponseEntity<EmailServiceResponse> callEmailNotificationService(String apiUri,
            EmailRequest request, WebClient client){

        return client.post()
                //.uri("email/sendMail")
                .uri(apiUri)
                .bodyValue(request)
                .retrieve()
                .toEntity(EmailServiceResponse.class)
                .block();
    }

    public static ResponseEntity<AuthenticationServiceResponse> callUserServiceDeleteUser(String apiUri
            ,String userName, WebClient webClient){

        return webClient.delete()
                //.uri("/deleteUser")
                .uri(apiUri)
                .header("userName", userName)
                .retrieve()
                .toEntity(AuthenticationServiceResponse.class)
                .block();
    }

    public static ResponseEntity<AuthenticationServiceResponse> callUserServiceActiveAndDeActivate(String apiUri
            ,String userName, Boolean isActive, WebClient webClient){

        return webClient.patch()
                .uri(apiUri)
                .headers(httpHeaders -> {
                    httpHeaders.set("userName", userName);
                    httpHeaders.set("isActive", String.valueOf(isActive));
                })
                .retrieve()
                .toEntity(AuthenticationServiceResponse.class)
                .block();
    }
}
