package com.intoThe.errorResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class RetailerExceptionResponse {
    private int status;
    private String StatusMsg;
    private LocalDateTime errorTimeStamp;
    private String path;

    public RetailerExceptionResponse(int status, String statusMsg, String pathDec) {
        this(status,statusMsg,LocalDateTime.now(),pathDec);
    }
}
