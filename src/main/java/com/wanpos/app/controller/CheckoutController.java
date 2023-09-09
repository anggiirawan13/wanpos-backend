package com.wanpos.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wanpos.app.dto.request.CheckoutRequest;
import com.wanpos.app.dto.response.BaseResponse;
import com.wanpos.app.impl.CheckoutServiceImpl;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Checkout")
@CrossOrigin
@RestController
@RequestMapping("/checkout")
public class CheckoutController {
    
    @Autowired
    private CheckoutServiceImpl checkoutServiceImpl;

    @PostMapping
    public BaseResponse saveCheckout(@RequestBody CheckoutRequest request) {
        return checkoutServiceImpl.saveCheckout(request);
    }

    @GetMapping("/{productCode}/{userCode}")
    public BaseResponse getCheckoutByProductAndUserCode(@PathVariable("productCode") String productCode, @PathVariable("userCode") String userCode) {
        return checkoutServiceImpl.getCheckoutByProductAndUserCode(productCode, userCode);
    }

    @GetMapping("/{code}")
    public BaseResponse getCheckoutByProductCode(@PathVariable("code") String code) {
        return checkoutServiceImpl.getCheckoutByProductCode(code);
    }

}
