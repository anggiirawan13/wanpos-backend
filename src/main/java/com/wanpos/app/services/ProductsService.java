package com.wanpos.app.services;

import com.wanpos.app.dtos.requests.ProductsInsertRequest;
import com.wanpos.app.dtos.requests.ProductsUpdateRequest;
import com.wanpos.app.dtos.responses.BaseResponse;

import java.util.List;

public interface ProductsService {

    BaseResponse saveProduct(List<ProductsInsertRequest> request);

    BaseResponse updateProductByUUID(ProductsUpdateRequest request);

    BaseResponse deleteProductByUUID(String uuid);

    BaseResponse getProducts(int page, int limit, String search);

    BaseResponse getProductByUUID(String uuid);

}
