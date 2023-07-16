package com.wanpos.app.controllers;

import com.wanpos.app.dtos.requests.OrdersInsertRequest;
import com.wanpos.app.dtos.requests.OrdersUpdateRequest;
import com.wanpos.app.dtos.responses.BaseResponse;
import com.wanpos.app.impls.OrdersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/orders")
public class OrdersController {

    @Autowired
    private OrdersServiceImpl ordersService;

    @PostMapping
    private BaseResponse saveOrder(@RequestBody OrdersInsertRequest request) {
        return ordersService.saveOrder(request);
    }

    @PutMapping
    private BaseResponse updateOrder(@RequestBody OrdersUpdateRequest request) {
        return ordersService.updateOrderByUUID(request);
    }

    @DeleteMapping(value = "/{uuid}")
    private BaseResponse deleteOrderByUUID(@PathVariable("uuid") String uuid) {
        return ordersService.deleteOrderByUUID(uuid);
    }

    @GetMapping
    private BaseResponse getOrders(@RequestParam(value = "page", required = false, defaultValue = "0") int page, @RequestParam(value = "limit", required = false, defaultValue = "0") int limit, @RequestParam(value = "search", required = false, defaultValue = "") String search) {
        return ordersService.getOrders(page, limit, search);
    }

    @GetMapping(value = "/{uuid}")
    private BaseResponse getOrderByUUID(@PathVariable("uuid") String uuid) {
        return ordersService.getOrderByUUID(uuid);
    }

}
