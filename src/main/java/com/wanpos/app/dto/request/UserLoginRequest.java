package com.wanpos.app.dto.request;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NonNull;

import java.io.Serializable;

@Data
@NonNull
@JsonAutoDetect
public class UserLoginRequest implements Serializable {

    @JsonProperty(value = "username")
    private String username;

    @JsonProperty(value = "password")
    private String password;

}
