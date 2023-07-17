package com.wanpos.app.impl;

import com.wanpos.app.dto.request.CategoryInsertRequest;
import com.wanpos.app.dto.request.CategoryUpdateRequest;
import com.wanpos.app.dto.response.BaseResponse;
import com.wanpos.app.entity.CategoryEntity;
import com.wanpos.app.repository.CategoryRepository;
import com.wanpos.app.service.CategoryService;
import com.wanpos.constanta.ResponseMessagesConst;
import com.wanpos.constanta.StatusConst;
import com.wanpos.handler.InternalServerErrorHandler;
import com.wanpos.helper.DateHelper;
import com.wanpos.helper.NullEmptyChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    private Logger logger;

    @Override
    public BaseResponse saveCategory(CategoryInsertRequest request) {
        try {
            CategoryEntity oldCategory = categoryRepository.getCategoryByCategoryCode(request.getCategoryCode());
            if (NullEmptyChecker.isNotNullOrEmpty(oldCategory)) {
                return new BaseResponse(HttpStatus.CONFLICT.value(), false, ResponseMessagesConst.ALREADY_EXIST.toString(), null);
            }

            CategoryEntity newCategory = new CategoryEntity();
            newCategory.setUuid(UUID.randomUUID().toString());
            newCategory.setCategoryCode(request.getCategoryCode());
            newCategory.setCategoryName(request.getCategoryName());
            newCategory.setStatus(StatusConst.ACTIVE.toString());

            Timestamp dateNow = DateHelper.getTimestampNow();

            newCategory.setCreatedAt(dateNow);
            newCategory.setCreatedBy("admin");
            newCategory.setModifiedAt(dateNow);
            newCategory.setModifiedBy("admin");

            CategoryEntity category = categoryRepository.save(newCategory);

            return new BaseResponse(HttpStatus.CREATED.value(), true, ResponseMessagesConst.INSERT_SUCCESS.toString(), category);
        } catch (Exception e) {
            return InternalServerErrorHandler.InternalServerError(e);
        }
    }

    @Override
    public BaseResponse updateCategoryByUUID(CategoryUpdateRequest request) {
        try {
            CategoryEntity oldCategory = categoryRepository.getCategoryByUUID(request.getUuid());
            if (NullEmptyChecker.isNullOrEmpty(oldCategory)) {
                return new BaseResponse(HttpStatus.NOT_FOUND.value(), false, ResponseMessagesConst.DATA_NOT_FOUND.toString(), null);
            }

            CategoryEntity updateCategory = categoryRepository.getCategoryByUUID(request.getUuid());
            updateCategory.setCategoryCode(request.getCategoryCode());
            updateCategory.setCategoryName(request.getCategoryName());
            updateCategory.setStatus(StatusConst.ACTIVE.toString());

            Timestamp dateNow = DateHelper.getTimestampNow();

            updateCategory.setModifiedAt(dateNow);
            updateCategory.setModifiedBy("admin");

            CategoryEntity category = categoryRepository.save(updateCategory);

            return new BaseResponse(HttpStatus.OK.value(), true, ResponseMessagesConst.UPDATE_SUCCESS.toString(), category);
        } catch (Exception e) {
            return InternalServerErrorHandler.InternalServerError(e);
        }
    }

    @Override
    public BaseResponse deleteCategoryByUUID(String uuid) {
        try {
            CategoryEntity oldCategory = categoryRepository.getCategoryByUUID(uuid);
            if (NullEmptyChecker.isNullOrEmpty(oldCategory)) {
                return new BaseResponse(HttpStatus.NOT_FOUND.value(), false, ResponseMessagesConst.DATA_NOT_FOUND.toString(), null);
            }

            categoryRepository.delete(oldCategory);

            return new BaseResponse(HttpStatus.OK.value(), true, ResponseMessagesConst.UPDATE_SUCCESS.toString(), null);
        } catch (Exception e) {
            return InternalServerErrorHandler.InternalServerError(e);
        }
    }

    @Override
    public BaseResponse getCategory(int page, int limit) {
        try {
            List<CategoryEntity> listCategory;
            if (NullEmptyChecker.isNullOrEmpty(page) || NullEmptyChecker.isNullOrEmpty(limit)) {
                listCategory = categoryRepository.findAll();
            } else {
                Pageable pageable = PageRequest.of(page, limit);
                listCategory = categoryRepository.findAll(pageable).toList();
            }

            return new BaseResponse(HttpStatus.OK.value(), true, ResponseMessagesConst.DATA_FOUND.toString(), listCategory);
        } catch (Exception e) {
            return InternalServerErrorHandler.InternalServerError(e);
        }
    }

    @Override
    public BaseResponse getCategoryByUUID(String uuid) {
        try {
            CategoryEntity listCategory = categoryRepository.getCategoryByUUID(uuid);

            return new BaseResponse(HttpStatus.OK.value(), true, ResponseMessagesConst.DATA_FOUND.toString(), listCategory);
        } catch (Exception e) {
            return InternalServerErrorHandler.InternalServerError(e);
        }
    }
}
