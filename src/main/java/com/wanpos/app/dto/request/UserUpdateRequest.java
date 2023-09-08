package com.wanpos.app.dto.request;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NonNull;

import java.io.Serializable;

@Data
@NonNull
@JsonAutoDetect
public class UserUpdateRequest implements Serializable {

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

    @JsonProperty(value = "role")
    private String role;

    @JsonProperty(value = "status")
    private String status;

}
