package com.wanpos.app.controller;

import com.wanpos.app.dto.request.OrderInsertRequest;
import com.wanpos.app.dto.request.OrderUpdateRequest;
import com.wanpos.app.dto.response.BaseResponse;
import com.wanpos.app.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/order")
public class OrderController {

    @Autowired
    private OrderServiceImpl orderService;

    @PostMapping
    private BaseResponse saveOrder(@RequestBody OrderInsertRequest request) {
        return orderService.saveOrder(request);
    }

    @PutMapping
    private BaseResponse updateOrder(@RequestBody OrderUpdateRequest request) {
        return orderService.updateOrderByUUID(request);
    }

    @DeleteMapping(value = "/{uuid}")
    private BaseResponse deleteOrderByUUID(@PathVariable("uuid") String uuid) {
        return orderService.deleteOrderByUUID(uuid);
    }

    @GetMapping
    private BaseResponse getOrder(@RequestParam(value = "page", required = false, defaultValue = "0") int page, @RequestParam(value = "limit", required = false, defaultValue = "0") int limit, @RequestParam(value = "search", required = false, defaultValue = "") String search) {
        return orderService.getOrder(page, limit, search);
    }

    @GetMapping(value = "/{uuid}")
    private BaseResponse getOrderByUUID(@PathVariable("uuid") String uuid) {
        return orderService.getOrderByUUID(uuid);
    }

}
