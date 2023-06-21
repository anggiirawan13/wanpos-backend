package com.wanpos.app.controllers;

import com.wanpos.app.dtos.requests.UsersRegisterRequest;
import com.wanpos.app.dtos.responses.BaseResponse;
import com.wanpos.app.impls.UsersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
public class UsersController {

    @Autowired
    private UsersServiceImpl usersService;

    @PostMapping(value = "/register")
    private BaseResponse usersRegister(@RequestBody UsersRegisterRequest request) {
        return usersService.usersRegister(request);
    }

}
