package com.wanpos.app.services;

import com.wanpos.app.dtos.requests.UsersLoginRequest;
import com.wanpos.app.dtos.requests.UsersRegisterRequest;
import com.wanpos.app.dtos.responses.BaseResponse;
import com.wanpos.app.entities.UsersEntity;

public interface UsersService {
    BaseResponse usersRegister(UsersRegisterRequest request);

    BaseResponse usersLogin(UsersLoginRequest request);

}
