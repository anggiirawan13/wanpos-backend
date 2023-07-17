package com.wanpos.app.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NonNull;

import java.io.Serializable;

@Data
@NonNull
public class UserRegisterRequest implements Serializable {

    @JsonProperty(value = "username")
    private String username;

    @JsonProperty(value = "fullname")
    private String fullname;

    @JsonProperty(value = "email")
    private String email;

    @JsonProperty(value = "password")
    private String password;

    @JsonProperty(value = "retype_password")
    private String retypePassword;

}
