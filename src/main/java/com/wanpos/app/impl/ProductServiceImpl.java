package com.wanpos.app.impl;

import com.wanpos.app.dto.request.ProductInsertRequest;
import com.wanpos.app.dto.request.ProductUpdateRequest;
import com.wanpos.app.dto.response.BaseResponse;
import com.wanpos.app.entity.ProductEntity;
import com.wanpos.app.repository.ProductRepository;
import com.wanpos.app.service.ProductService;
import com.wanpos.constanta.ResponseMessagesConst;
import com.wanpos.constanta.StatusConst;
import com.wanpos.handler.InternalServerErrorHandler;
import com.wanpos.helper.DateHelper;
import com.wanpos.helper.NullEmptyChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public BaseResponse saveProduct(ProductInsertRequest productRequest) {
        try {

            ProductEntity newProduct = new ProductEntity();
            newProduct.setUuid(UUID.randomUUID().toString());
            newProduct.setProductCode(productRequest.getProductCode());
            newProduct.setProductName(productRequest.getProductName());
            newProduct.setPrice(productRequest.getPrice());
            newProduct.setStock(productRequest.getStock());
            newProduct.setThumbnail(productRequest.getThumbnail());
            newProduct.setCategoryID(productRequest.getCategoryID());

            if (productRequest.getStatus().equalsIgnoreCase("active")) {
                newProduct.setStatus(StatusConst.ACTIVE.toString());
            } else {
                newProduct.setStatus(StatusConst.INACTIVE.toString());
            }

            Timestamp dateNow = DateHelper.getTimestampNow();

            newProduct.setCreatedAt(dateNow);
            newProduct.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
            newProduct.setModifiedAt(dateNow);
            newProduct.setModifiedBy(SecurityContextHolder.getContext().getAuthentication().getName());

            ProductEntity listNewProduct = productRepository.save(newProduct);

            return new BaseResponse(HttpStatus.CREATED.value(), true, ResponseMessagesConst.INSERT_SUCCESS.toString(), listNewProduct);
        } catch (Exception e) {
            return InternalServerErrorHandler.InternalServerError(e);
        }
    }

    @Override
    public BaseResponse updateProductByUUID(ProductUpdateRequest productRequest) {
        try {
            ProductEntity oldProduct = productRepository.findByUUID(productRequest.getUuid());
            if (NullEmptyChecker.isNullOrEmpty(oldProduct)) {
                return new BaseResponse(HttpStatus.NOT_FOUND.value(), false, ResponseMessagesConst.DATA_NOT_FOUND.toString(), null);
            }

            ProductEntity updateProduct = productRepository.findByUUID(productRequest.getUuid());
            updateProduct.setProductCode(productRequest.getProductCode());
            updateProduct.setProductName(productRequest.getProductName());
            updateProduct.setPrice(productRequest.getPrice());
            updateProduct.setThumbnail(productRequest.getThumbnail());
            updateProduct.setStock(productRequest.getStock());
            updateProduct.setCategoryID(productRequest.getCategoryID());

            if (productRequest.getStatus().equalsIgnoreCase("active")) {
                updateProduct.setStatus(StatusConst.ACTIVE.toString());
            } else {
                updateProduct.setStatus(StatusConst.INACTIVE.toString());
            }

            Timestamp dateNow = DateHelper.getTimestampNow();

            updateProduct.setModifiedAt(dateNow);
            updateProduct.setModifiedBy(SecurityContextHolder.getContext().getAuthentication().getName());

            ProductEntity product = productRepository.save(updateProduct);

            return new BaseResponse(HttpStatus.OK.value(), true, ResponseMessagesConst.UPDATE_SUCCESS.toString(), product);
        } catch (Exception e) {
            return InternalServerErrorHandler.InternalServerError(e);
        }
    }

    @Override
    public BaseResponse deleteProductByUUID(String uuid) {
        try {
            ProductEntity oldProduct = productRepository.findByUUID(uuid);
            if (NullEmptyChecker.isNullOrEmpty(oldProduct)) {
                return new BaseResponse(HttpStatus.NOT_FOUND.value(), false, ResponseMessagesConst.DATA_NOT_FOUND.toString(), null);
            }

            productRepository.delete(oldProduct);

            return new BaseResponse(HttpStatus.OK.value(), true, ResponseMessagesConst.UPDATE_SUCCESS.toString(), null);
        } catch (Exception e) {
            return InternalServerErrorHandler.InternalServerError(e);
        }
    }

    @Override
    public BaseResponse getProduct(int page, int limit, String search) {
        try {
            List<ProductEntity> listProduct;
            HashMap<String, Object> addEntity = new HashMap<>();
            if (page < 0 || NullEmptyChecker.isNullOrEmpty(limit)) {
                listProduct = productRepository.findAll();
            } else if (NullEmptyChecker.isNullOrEmpty(search)) {
                Pageable pageable = PageRequest.of(page, limit);
                Page<ProductEntity> pageProduct = productRepository.findAll(pageable);
                listProduct = pageProduct.toList();

                addEntity.put("totalPage", pageProduct.getTotalPages());
                addEntity.put("totalData", pageProduct.getTotalElements());
                addEntity.put("numberOfData", pageProduct.getNumberOfElements());
                addEntity.put("number", pageProduct.getNumber());
            } else {
                listProduct = productRepository.findByProductCodeOrName(search);
            }

            if (NullEmptyChecker.isNotNullOrEmpty(listProduct)) {
                return new BaseResponse(HttpStatus.FOUND.value(), true, ResponseMessagesConst.DATA_FOUND.toString(), listProduct, addEntity);
            }

            return new BaseResponse(HttpStatus.NOT_FOUND.value(), false, ResponseMessagesConst.DATA_NOT_FOUND.toString());
        } catch (Exception e) {
            return InternalServerErrorHandler.InternalServerError(e);
        }
    }

    @Override
    public BaseResponse getProductByUUID(String uuid) {
        try {
            ProductEntity listProduct = productRepository.findByUUID(uuid);

            if (NullEmptyChecker.isNotNullOrEmpty(listProduct)) {
                return new BaseResponse(HttpStatus.FOUND.value(), true, ResponseMessagesConst.DATA_FOUND.toString(), listProduct);
            }

            return new BaseResponse(HttpStatus.NOT_FOUND.value(), false, ResponseMessagesConst.DATA_NOT_FOUND.toString());
        } catch (Exception e) {
            return InternalServerErrorHandler.InternalServerError(e);
        }
    }
}
