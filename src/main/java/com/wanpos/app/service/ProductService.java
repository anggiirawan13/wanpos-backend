package com.wanpos.app.service;

import com.wanpos.app.dto.request.ProductInsertRequest;
import com.wanpos.app.dto.request.ProductUpdateRequest;
import com.wanpos.app.dto.response.BaseResponse;

import java.util.List;

public interface ProductService {

    BaseResponse saveProduct(ProductInsertRequest request);

    BaseResponse updateProductByUUID(ProductUpdateRequest request);

    BaseResponse deleteProductByUUID(String uuid);

    BaseResponse getProduct(int page, int limit, String search);

    BaseResponse getProductByUUID(String uuid);

}
