package com.wanpos.app.services;

import com.wanpos.app.dtos.requests.UsersRegisterRequest;
import com.wanpos.app.dtos.responses.BaseResponse;

public interface UsersService {
    BaseResponse usersRegister(UsersRegisterRequest request);

}
