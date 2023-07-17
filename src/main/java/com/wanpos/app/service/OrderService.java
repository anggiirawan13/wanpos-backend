package com.wanpos.app.service;

import com.wanpos.app.dto.request.OrderInsertRequest;
import com.wanpos.app.dto.request.OrderUpdateRequest;
import com.wanpos.app.dto.response.BaseResponse;

public interface OrderService {

    BaseResponse saveOrder(OrderInsertRequest request);

    BaseResponse updateOrderByUUID(OrderUpdateRequest request);

    BaseResponse deleteOrderByUUID(String uuid);

    BaseResponse getOrder(int page, int limit, String search);

    BaseResponse getOrderByUUID(String uuid);

}
