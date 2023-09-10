package com.wanpos.app.service;

import com.wanpos.app.dto.request.CheckoutRequest;
import com.wanpos.app.dto.response.BaseResponse;

public interface CheckoutService {
    
    BaseResponse save(CheckoutRequest request);

    BaseResponse findByProductCodeAndUserCode(String productCode, String userCode);

    BaseResponse findByProductCode(String code);

}
