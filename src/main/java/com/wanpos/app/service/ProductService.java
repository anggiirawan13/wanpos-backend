package com.wanpos.app.service;

import com.wanpos.app.dto.request.ProductRequest;
import com.wanpos.app.dto.response.BaseResponse;

public interface ProductService {
    
    BaseResponse save(ProductRequest request);

    BaseResponse update(ProductRequest request);

    BaseResponse findByProductCode(String code);

    BaseResponse findAll();

}
