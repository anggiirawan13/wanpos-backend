package com.wanpos.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wanpos.app.dto.request.UserLoginRequest;
import com.wanpos.app.dto.request.UserRegisterRequest;
import com.wanpos.app.dto.response.BaseResponse;
import com.wanpos.app.service.UserService;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Auth")
@CrossOrigin
@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping(value = "/register")
    private BaseResponse userRegister(@RequestBody UserRegisterRequest request) {
        return userService.userRegister(request);
    }

    @PostMapping(value = "/login")
    private BaseResponse userLogin(@RequestBody UserLoginRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        } catch (Exception e) {
            return new BaseResponse(HttpStatus.FORBIDDEN.value(), false, "LOGIN_FAILED", null);
        }

        return userService.userLogin(request);
    }

}
