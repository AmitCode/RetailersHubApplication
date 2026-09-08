package com.intoThe.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmailServiceResponse {
    private String statusCode;
    private String emailStatus;
    private String emailMessage;
}
