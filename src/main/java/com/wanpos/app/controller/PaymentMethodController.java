package com.wanpos.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wanpos.app.dto.response.BaseResponse;
import com.wanpos.app.impl.PaymentMethodServiceImpl;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Payment Method")
@CrossOrigin
@RestController
@RequestMapping("/payment/method")
public class PaymentMethodController {
    
    @Autowired
    private PaymentMethodServiceImpl paymentMethodServiceImpl;

    @PostMapping
    public BaseResponse save(@RequestParam("code") String code, @RequestParam("name") String name) {
        return paymentMethodServiceImpl.save(code, name);
    }

    @GetMapping
    public BaseResponse findAll() {
        return paymentMethodServiceImpl.findAll();
    }

}
