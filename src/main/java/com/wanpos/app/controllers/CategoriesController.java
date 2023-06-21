package com.wanpos.app.controllers;

import com.wanpos.app.dtos.requests.CategoriesInsertRequest;
import com.wanpos.app.dtos.requests.CategoriesUpdateRequest;
import com.wanpos.app.dtos.requests.UsersRegisterRequest;
import com.wanpos.app.dtos.responses.BaseResponse;
import com.wanpos.app.impls.CategoriesServiceImpl;
import com.wanpos.app.impls.UsersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/categories")
public class CategoriesController {

    @Autowired
    private CategoriesServiceImpl categoriesService;

    @PostMapping
    private BaseResponse saveCategory(@RequestBody CategoriesInsertRequest request) {
        return categoriesService.saveCategory(request);
    }

    @PutMapping
    private BaseResponse updateCategory(@RequestBody CategoriesUpdateRequest request) {
        return categoriesService.updateCategoryByUUID(request);
    }

    @DeleteMapping(value = "/{uuid}")
    private BaseResponse deleteCategoryByUUID(@PathVariable("uuid") String uuid) {
        return categoriesService.deleteCategoryByUUID(uuid);
    }

    @GetMapping
    private BaseResponse getCategories(@RequestParam("page") int page, @RequestParam("limit") int limit) {
        return categoriesService.getCategories(page, limit);
    }

    @GetMapping(value = "/{uuid}")
    private BaseResponse getCategoryByUUID(@PathVariable("uuid") String uuid) {
        return categoriesService.getCategoryByUUID(uuid);
    }

}
