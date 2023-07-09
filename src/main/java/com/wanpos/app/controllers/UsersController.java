package com.wanpos.app.controllers;

import com.wanpos.app.dtos.requests.UsersLoginRequest;
import com.wanpos.app.dtos.requests.UsersRegisterRequest;
import com.wanpos.app.dtos.responses.BaseResponse;
import com.wanpos.app.impls.UsersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
public class UsersController {

    @Autowired
    private UsersServiceImpl usersServiceImpl;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping(value = "/register")
    private BaseResponse usersRegister(@RequestBody UsersRegisterRequest request) {
        return usersServiceImpl.usersRegister(request);
    }

    @PostMapping(value = "/login")
    private BaseResponse usersLogin(@RequestBody UsersLoginRequest request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        } catch (Exception e) {
            new BaseResponse(HttpStatus.FORBIDDEN.value(), false, "LOGIN_FAILED", null);
        }
        return usersServiceImpl.usersLogin(request);
    }

}
