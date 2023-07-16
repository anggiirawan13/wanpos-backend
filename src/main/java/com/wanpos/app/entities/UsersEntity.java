package com.wanpos.app.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class UsersEntity extends BaseEntity {

    @Column(name = "fullname", nullable = false, length = 100)
    @JsonProperty("fullname")
    private String fullname;

    @Column(name = "email", unique = true, nullable = false, length = 100)
    @JsonProperty("email")
    private String email;

    @Column(name = "username", unique = true, nullable = false, length = 50)
    @JsonProperty("username")
    private String username;

    @Column(name = "password", nullable = false)
    @JsonProperty("password")
    private String password;

    @Column(name = "role", nullable = false, length = 10)
    @JsonProperty("role")
    private String role;

}
