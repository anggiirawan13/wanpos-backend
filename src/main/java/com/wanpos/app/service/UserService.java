package com.wanpos.app.service;

import com.wanpos.app.dto.request.UserLoginRequest;
import com.wanpos.app.dto.request.UserRegisterRequest;
import com.wanpos.app.dto.response.BaseResponse;

public interface UserService {
    BaseResponse userRegister(UserRegisterRequest request);

    BaseResponse userLogin(UserLoginRequest request);

    BaseResponse findAll(int page, int limit, String search);

    BaseResponse findByUuid(String uuid);

}
