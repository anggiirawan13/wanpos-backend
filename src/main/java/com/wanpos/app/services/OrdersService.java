package com.wanpos.app.services;

import com.wanpos.app.dtos.requests.OrdersInsertRequest;
import com.wanpos.app.dtos.requests.OrdersUpdateRequest;
import com.wanpos.app.dtos.responses.BaseResponse;

import java.util.List;

public interface OrdersService {

    BaseResponse saveOrder(OrdersInsertRequest request);

    BaseResponse updateOrderByUUID(OrdersUpdateRequest request);

    BaseResponse deleteOrderByUUID(String uuid);

    BaseResponse getOrders(int page, int limit, String search);

    BaseResponse getOrderByUUID(String uuid);

}
