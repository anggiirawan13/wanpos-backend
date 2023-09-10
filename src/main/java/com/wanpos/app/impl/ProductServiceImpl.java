package com.wanpos.app.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.wanpos.app.dto.request.ProductRequest;
import com.wanpos.app.dto.response.BaseResponse;
import com.wanpos.app.entity.ProductEntity;
import com.wanpos.app.repository.ProductRepository;
import com.wanpos.app.service.ProductService;
import com.wanpos.constanta.ResponseMessagesConst;
import com.wanpos.handler.InternalServerErrorHandler;

@Service
public class ProductServiceImpl implements ProductService {
    
    @Autowired
    private ProductRepository productRepository;

    @Override
    public BaseResponse save(ProductRequest request) {
        try {
            ProductEntity productExist = productRepository.findByProductCode(request.getProductCode());
            if (productExist != null) {
                return new BaseResponse(HttpStatus.CONFLICT.value(), false, ResponseMessagesConst.ALREADY_EXIST.toString());
            }
        
            ProductEntity newData = new ProductEntity();
            newData.setUuid(UUID.randomUUID().toString());
            newData.setProductCode(request.getProductCode());
            newData.setProductName(request.getProductName());
            newData.setBuyingPrice(request.getBuyingPrice());
            newData.setSellingPrice(request.getSellingPrice());
            newData.setStock(request.getStock());

            Timestamp dateNow = new Timestamp(new Date().getTime());

            newData.setCreatedAt(dateNow);
            newData.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
            newData.setModifiedAt(dateNow);
            newData.setModifiedBy(SecurityContextHolder.getContext().getAuthentication().getName());

            productRepository.save(newData);

            return new BaseResponse(HttpStatus.CREATED.value(), true, ResponseMessagesConst.INSERT_SUCCESS.toString());
        } catch (Exception e) {
            return InternalServerErrorHandler.InternalServerError(e);
        }
    }

    @Override
    public BaseResponse update(ProductRequest request) {
        try {
            ProductEntity productExist = productRepository.findByProductCode(request.getProductCode());
            if (productExist == null) {
                return new BaseResponse(HttpStatus.NOT_FOUND.value(), false, ResponseMessagesConst.DATA_NOT_FOUND.toString());
            }

            productExist.setUuid(UUID.randomUUID().toString());
            productExist.setProductCode(request.getProductCode());
            productExist.setProductName(request.getProductName());
            productExist.setBuyingPrice(request.getBuyingPrice());
            productExist.setSellingPrice(request.getSellingPrice());
            productExist.setStock(request.getStock());

            Timestamp dateNow = new Timestamp(new Date().getTime());

            productExist.setCreatedAt(dateNow);
            productExist.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
            productExist.setModifiedAt(dateNow);
            productExist.setModifiedBy(SecurityContextHolder.getContext().getAuthentication().getName());

            productRepository.save(productExist);

            return new BaseResponse(HttpStatus.CREATED.value(), true, ResponseMessagesConst.UPDATE_SUCCESS.toString());
        } catch (Exception e) {
            return InternalServerErrorHandler.InternalServerError(e);
        }
    }

    @Override
    public BaseResponse findByProductCode(String code) {
        try {
            ProductEntity resultProduct = productRepository.findByProductCode(code);
            if (resultProduct == null) {
                return new BaseResponse(HttpStatus.NOT_FOUND.value(), false, ResponseMessagesConst.DATA_NOT_FOUND.toString());
            }
        
            return new BaseResponse(HttpStatus.FOUND.value(), true, ResponseMessagesConst.DATA_FOUND.toString(), resultProduct);
        } catch (Exception e) {
            return InternalServerErrorHandler.InternalServerError(e);
        }
    }

    @Override
    public BaseResponse findAll() {
        try {
            List<ProductEntity> listProduct = productRepository.findAll();
            if (listProduct == null || listProduct.isEmpty()) {
                return new BaseResponse(HttpStatus.NOT_FOUND.value(), false, ResponseMessagesConst.DATA_NOT_FOUND.toString());
            }

            return new BaseResponse(HttpStatus.FOUND.value(), true, ResponseMessagesConst.DATA_FOUND.toString(), listProduct);
        } catch (Exception e) {
            return InternalServerErrorHandler.InternalServerError(e);
        }
    }

}
