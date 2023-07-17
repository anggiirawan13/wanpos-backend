package com.wanpos.app.controller;

import com.wanpos.app.dto.request.CategoryInsertRequest;
import com.wanpos.app.dto.request.CategoryUpdateRequest;
import com.wanpos.app.dto.response.BaseResponse;
import com.wanpos.app.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/category")
public class CategoryController {

    @Autowired
    private CategoryServiceImpl categoryService;

    @PostMapping
    private BaseResponse saveCategory(@RequestBody CategoryInsertRequest request) {
        return categoryService.saveCategory(request);
    }

    @PutMapping
    private BaseResponse updateCategory(@RequestBody CategoryUpdateRequest request) {
        return categoryService.updateCategoryByUUID(request);
    }

    @DeleteMapping(value = "/{uuid}")
    private BaseResponse deleteCategoryByUUID(@PathVariable("uuid") String uuid) {
        return categoryService.deleteCategoryByUUID(uuid);
    }

    @GetMapping
    private BaseResponse getCategory(@RequestParam(value = "page", required = false, defaultValue = "0") int page, @RequestParam(value = "limit", required = false, defaultValue = "0") int limit) {
        return categoryService.getCategory(page, limit);
    }

    @GetMapping(value = "/{uuid}")
    private BaseResponse getCategoryByUUID(@PathVariable("uuid") String uuid) {
        return categoryService.getCategoryByUUID(uuid);
    }

}
