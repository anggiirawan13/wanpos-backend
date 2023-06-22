package com.wanpos.app.controllers;

import com.wanpos.app.dtos.requests.ProductsInsertRequest;
import com.wanpos.app.dtos.requests.ProductsUpdateRequest;
import com.wanpos.app.dtos.responses.BaseResponse;
import com.wanpos.app.impls.ProductsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/products")
public class ProductsController {

    @Autowired
    private ProductsServiceImpl productsService;

    @PostMapping
    private BaseResponse saveProduct(@RequestBody List<ProductsInsertRequest> request) {
        return productsService.saveProduct(request);
    }

    @PutMapping
    private BaseResponse updateProduct(@RequestBody ProductsUpdateRequest request) {
        return productsService.updateProductByUUID(request);
    }

    @DeleteMapping(value = "/{uuid}")
    private BaseResponse deleteProductByUUID(@PathVariable("uuid") String uuid) {
        return productsService.deleteProductByUUID(uuid);
    }

    @GetMapping
    private BaseResponse getProducts(@RequestParam("page") int page, @RequestParam("limit") int limit) {
        return productsService.getProducts(page, limit);
    }

    @GetMapping(value = "/{uuid}")
    private BaseResponse getProductByUUID(@PathVariable("uuid") String uuid) {
        return productsService.getProductByUUID(uuid);
    }

}
