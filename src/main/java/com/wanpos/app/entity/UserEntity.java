package com.wanpos.app.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class UserEntity extends BaseEntity {

    @Column(name = "company_code", unique = true, nullable = false, length = 30)
    @JsonProperty("company_code")
    private String companyCode;

    @Column(name = "user_code", unique = true, nullable = false, length = 20)
    @JsonProperty("user_code")
    private String userCode;

    @Column(name = "full_name", nullable = false, length = 100)
    @JsonProperty("full_name")
    private String fullName;

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

    @Column(name = "status", nullable = false, length = 10)
    @JsonProperty("status")
    private String status;

}
