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

    private String messages;

    private Object result;

}
