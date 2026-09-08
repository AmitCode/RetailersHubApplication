package com.intoThe.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class OtpServiceResponse {
    private String status;
    private String messageToken;
    private String otp;

    public OtpServiceResponse setStatus(String status) {
        this.status = status;
        return this;
    }

    public OtpServiceResponse setMessageToken(String messageToken) {
        this.messageToken = messageToken;
        return this;
    }

    public OtpServiceResponse setOtp(String otp) {
        this.otp = otp;
        return this;
    }

    public static OtpServiceResponse createOtpResponse(){
        return new OtpServiceResponse();
    }
}
