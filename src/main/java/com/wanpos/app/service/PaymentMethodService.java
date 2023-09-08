package com.wanpos.app.service;

import com.wanpos.app.dto.response.BaseResponse;

public interface PaymentMethodService {
    

    BaseResponse savePaymentMethod(String code, String name);

    BaseResponse getAllPaymentMethod();

}
