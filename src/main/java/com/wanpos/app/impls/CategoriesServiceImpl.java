package com.wanpos.app.impls;

import com.wanpos.app.dtos.requests.CategoriesInsertRequest;
import com.wanpos.app.dtos.requests.CategoriesUpdateRequest;
import com.wanpos.app.dtos.responses.BaseResponse;
import com.wanpos.app.entities.CategoriesEntity;
import com.wanpos.app.repositories.CategoriesRepository;
import com.wanpos.app.services.CategoriesService;
import com.wanpos.constanta.ResponseMessagesConst;
import com.wanpos.constanta.StatusConst;
import com.wanpos.handler.InternalServerError;
import com.wanpos.helper.DateHelper;
import com.wanpos.helper.NullEmptyChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@Service
public class CategoriesServiceImpl implements CategoriesService {

    @Autowired
    private CategoriesRepository categoriesRepository;

    private Logger logger;

    @Override
    public BaseResponse saveCategory(CategoriesInsertRequest request) {
        try {
            CategoriesEntity oldCategory = categoriesRepository.getCategoryByCategoryCode(request.getCategoryCode());
            if (NullEmptyChecker.isNotNullOrEmpty(oldCategory)) {
                return new BaseResponse(HttpStatus.CONFLICT.value(), false, ResponseMessagesConst.ALREADY_EXIST.toString(), null);
            }

            CategoriesEntity newCategory = new CategoriesEntity();
            newCategory.setUuid(UUID.randomUUID().toString());
            newCategory.setCategoryCode(request.getCategoryCode());
            newCategory.setCategoryName(request.getCategoryName());
            newCategory.setStatus(StatusConst.ACTIVE.toString());

            Timestamp dateNow = DateHelper.getTimestampNow();

            newCategory.setCreatedAt(dateNow);
            newCategory.setCreatedBy("admin");
            newCategory.setModifiedAt(dateNow);
            newCategory.setModifiedBy("admin");

            CategoriesEntity category = categoriesRepository.save(newCategory);

            return new BaseResponse(HttpStatus.CREATED.value(), true, ResponseMessagesConst.INSERT_SUCCESS.toString(), category);
        } catch (Exception e) {
            return InternalServerError.InternalServerError(e);
        }
    }

    @Override
    public BaseResponse updateCategoryByUUID(CategoriesUpdateRequest request) {
        try {
            CategoriesEntity oldCategory = categoriesRepository.getCategoryByUUID(request.getUuid());
            if (NullEmptyChecker.isNullOrEmpty(oldCategory)) {
                return new BaseResponse(HttpStatus.NOT_FOUND.value(), false, ResponseMessagesConst.DATA_NOT_FOUND.toString(), null);
            }

            CategoriesEntity updateCategory = categoriesRepository.getCategoryByUUID(request.getUuid());
            updateCategory.setCategoryCode(request.getCategoryCode());
            updateCategory.setCategoryName(request.getCategoryName());
            updateCategory.setStatus(StatusConst.ACTIVE.toString());

            Timestamp dateNow = DateHelper.getTimestampNow();

            updateCategory.setModifiedAt(dateNow);
            updateCategory.setModifiedBy("admin");

            CategoriesEntity category = categoriesRepository.save(updateCategory);

            return new BaseResponse(HttpStatus.OK.value(), true, ResponseMessagesConst.UPDATE_SUCCESS.toString(), category);
        } catch (Exception e) {
            return InternalServerError.InternalServerError(e);
        }
    }

    @Override
    public BaseResponse deleteCategoryByUUID(String uuid) {
        try {
            CategoriesEntity oldCategory = categoriesRepository.getCategoryByUUID(uuid);
            if (NullEmptyChecker.isNullOrEmpty(oldCategory)) {
                return new BaseResponse(HttpStatus.NOT_FOUND.value(), false, ResponseMessagesConst.DATA_NOT_FOUND.toString(), null);
            }

            categoriesRepository.delete(oldCategory);

            return new BaseResponse(HttpStatus.OK.value(), true, ResponseMessagesConst.UPDATE_SUCCESS.toString(), null);
        } catch (Exception e) {
            return InternalServerError.InternalServerError(e);
        }
    }

    @Override
    public BaseResponse getCategories(int page, int limit) {
        try {
            List<CategoriesEntity> listCategory;
            if (NullEmptyChecker.isNullOrEmpty(page) || NullEmptyChecker.isNullOrEmpty(limit)) {
                listCategory = categoriesRepository.findAll();
            } else {
                Pageable pageable = PageRequest.of(page, limit);
                listCategory = categoriesRepository.findAll(pageable).toList();
            }

            return new BaseResponse(HttpStatus.OK.value(), true, ResponseMessagesConst.DATA_FOUND.toString(), listCategory);
        } catch (Exception e) {
            return InternalServerError.InternalServerError(e);
        }
    }

    @Override
    public BaseResponse getCategoryByUUID(String uuid) {
        try {
            CategoriesEntity listCategory = categoriesRepository.getCategoryByUUID(uuid);

            return new BaseResponse(HttpStatus.OK.value(), true, ResponseMessagesConst.DATA_FOUND.toString(), listCategory);
        } catch (Exception e) {
            return InternalServerError.InternalServerError(e);
        }
    }
}
