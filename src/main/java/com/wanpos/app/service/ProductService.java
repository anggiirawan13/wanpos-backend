package com.wanpos.app.service;

import com.wanpos.app.dto.request.ProductRequest;
import com.wanpos.app.dto.response.BaseResponse;

public interface ProductService {
    
    BaseResponse saveProduct(ProductRequest request);

    BaseResponse updateProduct(ProductRequest request);

    BaseResponse getProductByProductCode(String code);

    BaseResponse getAllProduct();

}
