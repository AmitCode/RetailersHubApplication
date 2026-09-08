package com.user.service.app.dtos.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Schema(
        name = "UserServiceOperationResponse",
        description = "Standard response returned by the User Service for user operations"
)
public class UserServiceOprResponse {

    @Schema(
            description = "HTTP status code associated with the operation",
            example = "201"
    )
    private String statusCode;

    @Schema(
            description = "Descriptive message indicating the result of the operation",
            example = "User has been added successfully with id: 1001"
    )
    private String responseMsg;

    @Schema(
            description = "Indicates whether the requested operation was successful",
            example = "true"
    )
    private Boolean isOprSuccess;

    public UserServiceOprResponse setStatusCode(String statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    public UserServiceOprResponse setResponseMsg(String responseMsg) {
        this.responseMsg = responseMsg;
        return this;
    }

    public UserServiceOprResponse setIsOprSuccess(Boolean isOprSuccess) {
        this.isOprSuccess = isOprSuccess;
        return this;
    }

    public static UserServiceOprResponse createResponse() {
        return new UserServiceOprResponse();
    }
}
