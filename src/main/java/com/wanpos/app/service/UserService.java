package com.wanpos.app.service;

import com.wanpos.app.dto.request.UserLoginRequest;
import com.wanpos.app.dto.request.UserRegisterRequest;
import com.wanpos.app.dto.response.BaseResponse;

public interface UserService {
    BaseResponse userRegister(UserRegisterRequest request);

    BaseResponse userLogin(UserLoginRequest request);

    BaseResponse getUser(int page, int limit, String search);

    BaseResponse getUserByUUID(String uuid);

}
