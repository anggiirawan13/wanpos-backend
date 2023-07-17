package com.wanpos.app.service;

import com.wanpos.app.dto.request.CategoryInsertRequest;
import com.wanpos.app.dto.request.CategoryUpdateRequest;
import com.wanpos.app.dto.response.BaseResponse;

public interface CategoryService {

    BaseResponse saveCategory(CategoryInsertRequest request);

    BaseResponse updateCategoryByUUID(CategoryUpdateRequest request);

    BaseResponse deleteCategoryByUUID(String uuid);

    BaseResponse getCategory(int page, int limit);

    BaseResponse getCategoryByUUID(String uuid);

}
