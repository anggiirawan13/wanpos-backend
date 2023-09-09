package com.wanpos.app.service;

import com.wanpos.app.dto.request.CheckoutRequest;
import com.wanpos.app.dto.response.BaseResponse;

public interface CheckoutService {
    
    BaseResponse saveCheckout(CheckoutRequest request);

    BaseResponse getCheckoutByProductAndUserCode(String productCode, String userCode);

    BaseResponse getCheckoutByProductCode(String code);

}
