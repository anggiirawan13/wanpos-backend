package com.wanpos.app.impls;

import com.wanpos.app.dtos.requests.ProductsInsertRequest;
import com.wanpos.app.dtos.requests.ProductsUpdateRequest;
import com.wanpos.app.dtos.responses.BaseResponse;
import com.wanpos.app.entities.ProductsEntity;
import com.wanpos.app.repositories.ProductsRepository;
import com.wanpos.app.services.ProductsService;
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
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@Service
public class ProductsServiceImpl implements ProductsService {

    @Autowired
    private ProductsRepository productsRepository;

    private Logger logger;

    @Override
    public BaseResponse saveProduct(List<ProductsInsertRequest> listProductRequest) {
        try {
            List<ProductsEntity> listProduct = new ArrayList<>();
            for (ProductsInsertRequest prod : listProductRequest) {
                ProductsEntity newProduct = new ProductsEntity();
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

            List<ProductsEntity> listNewProduct = productsRepository.saveAll(listProduct);

            return new BaseResponse(HttpStatus.CREATED.value(), true, ResponseMessagesConst.INSERT_SUCCESS.toString(), listNewProduct  );
        } catch (Exception e) {
            return InternalServerError.InternalServerError(e);
        }
    }

    @Override
    public BaseResponse updateProductByUUID(ProductsUpdateRequest request) {
        try {
            ProductsEntity oldProduct = productsRepository.getProductByUUID(request.getUuid());
            if (NullEmptyChecker.isNullOrEmpty(oldProduct)) {
                return new BaseResponse(HttpStatus.NOT_FOUND.value(), false, ResponseMessagesConst.DATA_NOT_FOUND.toString(), null);
            }

            ProductsEntity updateProduct = productsRepository.getProductByUUID(request.getUuid());
            updateProduct.setProductCode(request.getProductCode());
            updateProduct.setProductName(request.getProductName());
            updateProduct.setPrice(request.getPrice());
            updateProduct.setThumbnail(request.getThumbnail());
            updateProduct.setStatus(StatusConst.ACTIVE.toString());

            Timestamp dateNow = DateHelper.getTimestampNow();

            updateProduct.setModifiedAt(dateNow);
            updateProduct.setModifiedBy("admin");

            ProductsEntity product = productsRepository.save(updateProduct);

            return new BaseResponse(HttpStatus.OK.value(), true, ResponseMessagesConst.UPDATE_SUCCESS.toString(), product);
        } catch (Exception e) {
            return InternalServerError.InternalServerError(e);
        }
    }

    @Override
    public BaseResponse deleteProductByUUID(String uuid) {
        try {
            ProductsEntity oldProduct = productsRepository.getProductByUUID(uuid);
            if (NullEmptyChecker.isNullOrEmpty(oldProduct)) {
                return new BaseResponse(HttpStatus.NOT_FOUND.value(), false, ResponseMessagesConst.DATA_NOT_FOUND.toString(), null);
            }

            productsRepository.delete(oldProduct);

            return new BaseResponse(HttpStatus.OK.value(), true, ResponseMessagesConst.UPDATE_SUCCESS.toString(), null);
        } catch (Exception e) {
            return InternalServerError.InternalServerError(e);
        }
    }

    @Override
    public BaseResponse getProducts(int page, int limit) {
        try {
            Pageable pageable = PageRequest.of(page, limit);
            Page<ProductsEntity> listProduct = productsRepository.findAll(pageable);

            return new BaseResponse(HttpStatus.OK.value(), true, ResponseMessagesConst.DATA_FOUND.toString(), listProduct.toList());
        } catch (Exception e) {
            return InternalServerError.InternalServerError(e);
        }
    }

    @Override
    public BaseResponse getProductByUUID(String uuid) {
        try {
            ProductsEntity listProduct = productsRepository.getProductByUUID(uuid);

            return new BaseResponse(HttpStatus.OK.value(), true, ResponseMessagesConst.DATA_FOUND.toString(), listProduct);
        } catch (Exception e) {
            return InternalServerError.InternalServerError(e);
        }
    }
}
