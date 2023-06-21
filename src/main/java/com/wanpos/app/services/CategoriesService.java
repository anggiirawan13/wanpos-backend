package com.wanpos.app.services;

import com.wanpos.app.dtos.requests.CategoriesInsertRequest;
import com.wanpos.app.dtos.requests.CategoriesUpdateRequest;
import com.wanpos.app.dtos.responses.BaseResponse;

public interface CategoriesService {

    BaseResponse saveCategory(CategoriesInsertRequest request);

    BaseResponse updateCategoryByUUID(CategoriesUpdateRequest request);

    BaseResponse deleteCategoryByUUID(String uuid);

    BaseResponse getCategories(int page, int limit);

    BaseResponse getCategoryByUUID(String uuid);

}
