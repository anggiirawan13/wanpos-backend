package com.wanpos.app.dtos.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse {

    @JsonProperty(namespace = "status_code")
    private int statusCode;

    @JsonProperty(namespace = "success")
    private boolean success;

    @JsonProperty(namespace = "messages")
    private String messages;

    @JsonProperty(namespace = "result")
    private Object result;

}
