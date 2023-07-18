package com.wanpos.app.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse {

    public BaseResponse(int statusCode, boolean success, String messages) {
        this.statusCode = statusCode;
        this.success = success;
        this.messages = messages;
    }

    public BaseResponse(int statusCode, boolean success, String messages, Object result) {
        this.statusCode = statusCode;
        this.success = success;
        this.messages = messages;
        this.result = result;
    }

    @JsonProperty(namespace = "status_code")
    private int statusCode;

    @JsonProperty(namespace = "success")
    private boolean success;

    @JsonProperty(namespace = "messages")
    private String messages;

    @JsonProperty(namespace = "result")
    private Object result;

    @JsonProperty(namespace = "additional_entity")
    private Object additionalEntity;

}
