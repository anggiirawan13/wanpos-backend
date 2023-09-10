package com.wanpos.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wanpos.app.dto.request.ProductRequest;
import com.wanpos.app.dto.response.BaseResponse;
import com.wanpos.app.impl.ProductServiceImpl;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Product")
@CrossOrigin
@RestController
@RequestMapping("/product")
public class ProductController {
    
    @Autowired
    private ProductServiceImpl productServiceImpl;

    @PostMapping
    public BaseResponse save(@RequestBody ProductRequest request) {
        return productServiceImpl.save(request);
    }

    @PutMapping
    public BaseResponse update(@RequestBody ProductRequest request) {
        return productServiceImpl.update(request);
    }

    @GetMapping("/{code}")
    public BaseResponse findByProductCode(@PathVariable("code") String code) {
        return productServiceImpl.findByProductCode(code);
    }

    @GetMapping
    public BaseResponse findAll() {
        return productServiceImpl.findAll();
    }

    @DeleteMapping("/{code}")
    public BaseResponse deleteByProductCode(@PathVariable("code") String code) {
        return productServiceImpl.deleteByProductCode(code);
    }

}
