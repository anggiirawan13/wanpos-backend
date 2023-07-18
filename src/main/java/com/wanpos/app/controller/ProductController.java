package com.wanpos.app.controller;

import com.wanpos.app.dto.request.ProductInsertRequest;
import com.wanpos.app.dto.request.ProductUpdateRequest;
import com.wanpos.app.dto.response.BaseResponse;
import com.wanpos.app.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    private ProductServiceImpl productService;

    @PostMapping
    private BaseResponse saveProduct(@RequestBody ProductInsertRequest request) {
        return productService.saveProduct(request);
    }

    @PutMapping
    private BaseResponse updateProduct(@RequestBody ProductUpdateRequest request) {
        return productService.updateProductByUUID(request);
    }

    @DeleteMapping(value = "/{uuid}")
    private BaseResponse deleteProductByUUID(@PathVariable("uuid") String uuid) {
        return productService.deleteProductByUUID(uuid);
    }

    @GetMapping
    private BaseResponse getProduct(@RequestParam(value = "page", required = false, defaultValue = "0") int page, @RequestParam(value = "limit", required = false, defaultValue = "0") int limit, @RequestParam(value = "search", required = false, defaultValue = "") String search) {
        return productService.getProduct(page, limit, search);
    }

    @GetMapping(value = "/{uuid}")
    private BaseResponse getProductByUUID(@PathVariable("uuid") String uuid) {
        return productService.getProductByUUID(uuid);
    }

}
