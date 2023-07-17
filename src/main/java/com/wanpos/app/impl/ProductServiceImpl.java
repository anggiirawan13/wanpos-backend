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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    private Logger logger;

    @Override
    public BaseResponse saveProduct(List<ProductInsertRequest> listProductRequest) {
        try {
            List<ProductEntity> listProduct = new ArrayList<>();
            for (ProductInsertRequest prod : listProductRequest) {
                ProductEntity newProduct = new ProductEntity();
                newProduct.setUuid(UUID.randomUUID().toString());
                newProduct.setProductCode(prod.getProductCode());
                newProduct.setProductName(prod.getProductName());
                newProduct.setPrice(prod.getPrice());
                newProduct.setThumbnail(prod.getThumbnail());
                newProduct.setCategoryID(new Long(prod.getCategoryID()));
                newProduct.setStatus(StatusConst.ACTIVE.toString());

                Timestamp dateNow = DateHelper.getTimestampNow();

                newProduct.setCreatedAt(dateNow);
                newProduct.setCreatedBy("admin");
                newProduct.setModifiedAt(dateNow);
                newProduct.setModifiedBy("admin");

                listProduct.add(newProduct);
            }

            List<ProductEntity> listNewProduct = productRepository.saveAll(listProduct);

            return new BaseResponse(HttpStatus.CREATED.value(), true, ResponseMessagesConst.INSERT_SUCCESS.toString(), listNewProduct  );
        } catch (Exception e) {
            return InternalServerErrorHandler.InternalServerError(e);
        }
    }

    @Override
    public BaseResponse updateProductByUUID(ProductUpdateRequest request) {
        try {
            ProductEntity oldProduct = productRepository.getProductByUUID(request.getUuid());
            if (NullEmptyChecker.isNullOrEmpty(oldProduct)) {
                return new BaseResponse(HttpStatus.NOT_FOUND.value(), false, ResponseMessagesConst.DATA_NOT_FOUND.toString(), null);
            }

            ProductEntity updateProduct = productRepository.getProductByUUID(request.getUuid());
            updateProduct.setProductCode(request.getProductCode());
            updateProduct.setProductName(request.getProductName());
            updateProduct.setPrice(request.getPrice());
            updateProduct.setThumbnail(request.getThumbnail());
            updateProduct.setStatus(StatusConst.ACTIVE.toString());

            Timestamp dateNow = DateHelper.getTimestampNow();

            updateProduct.setModifiedAt(dateNow);
            updateProduct.setModifiedBy("admin");

            ProductEntity product = productRepository.save(updateProduct);

            return new BaseResponse(HttpStatus.OK.value(), true, ResponseMessagesConst.UPDATE_SUCCESS.toString(), product);
        } catch (Exception e) {
            return InternalServerErrorHandler.InternalServerError(e);
        }
    }

    @Override
    public BaseResponse deleteProductByUUID(String uuid) {
        try {
            ProductEntity oldProduct = productRepository.getProductByUUID(uuid);
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
            if (NullEmptyChecker.isNullOrEmpty(page) || NullEmptyChecker.isNullOrEmpty(limit)) {
                listProduct = productRepository.findAll();
            } else if (NullEmptyChecker.isNullOrEmpty(search)) {
                Pageable pageable = PageRequest.of(page, limit);
                listProduct = productRepository.findAll(pageable).toList();
            } else {
                listProduct = productRepository.getProductByProductCodeOrName(search);
            }

            return new BaseResponse(HttpStatus.OK.value(), true, ResponseMessagesConst.DATA_FOUND.toString(), listProduct);
        } catch (Exception e) {
            return InternalServerErrorHandler.InternalServerError(e);
        }
    }

    @Override
    public BaseResponse getProductByUUID(String uuid) {
        try {
            ProductEntity listProduct = productRepository.getProductByUUID(uuid);

            return new BaseResponse(HttpStatus.OK.value(), true, ResponseMessagesConst.DATA_FOUND.toString(), listProduct);
        } catch (Exception e) {
            return InternalServerErrorHandler.InternalServerError(e);
        }
    }
}
