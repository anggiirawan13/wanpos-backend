package com.wanpos.app.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.wanpos.app.dto.request.CheckoutItemRequest;
import com.wanpos.app.dto.request.CheckoutRequest;
import com.wanpos.app.dto.response.BaseResponse;
import com.wanpos.app.dto.response.CheckoutItemResponse;
import com.wanpos.app.dto.response.CheckoutResponse;
import com.wanpos.app.entity.CheckoutEntity;
import com.wanpos.app.entity.CheckoutItemEntity;
import com.wanpos.app.repository.CheckoutItemRepository;
import com.wanpos.app.repository.CheckoutRepository;
import com.wanpos.app.service.CheckoutService;
import com.wanpos.constanta.ResponseMessagesConst;
import com.wanpos.handler.InternalServerErrorHandler;

@Service
public class CheckoutServiceImpl implements CheckoutService {
    
    @Autowired
    private CheckoutRepository checkoutRepository;

    @Autowired
    private CheckoutItemRepository checkoutItemRepository;

    @Override
    public BaseResponse save(CheckoutRequest request) {
        try {
            CheckoutEntity newDataHeader = new CheckoutEntity();
            newDataHeader.setUuid(UUID.randomUUID().toString());
            newDataHeader.setCompanyCode(request.getCompanyCode());
            newDataHeader.setCheckoutNumber(request.getCheckoutNumber());
            newDataHeader.setGrossAmount(request.getGrossAmount());
            newDataHeader.setNetAmount(request.getNetAmount());
            newDataHeader.setUserCode(request.getUserCode());

            Timestamp dateNow = new Timestamp(new Date().getTime());

            newDataHeader.setCreatedAt(dateNow);
            newDataHeader.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
            newDataHeader.setModifiedAt(dateNow);
            newDataHeader.setModifiedBy(SecurityContextHolder.getContext().getAuthentication().getName());
            
            List<CheckoutItemEntity> listItem = new ArrayList<>();
            for (CheckoutItemRequest item : request.getListItem()) {
                CheckoutItemEntity dataItem = new CheckoutItemEntity();
                dataItem.setCheckoutNumber(item.getCheckoutNumber());
                dataItem.setLineAmount(item.getLineAmount());
                dataItem.setProductCode(item.getProductCode());
                dataItem.setQuantity(item.getQuantity());
                dataItem.setSellingPrice(item.getSellingPrice());
                
                listItem.add(dataItem);
            }

            checkoutRepository.save(newDataHeader);
            checkoutItemRepository.saveAll(listItem);

            return new BaseResponse(HttpStatus.CREATED.value(), true, ResponseMessagesConst.INSERT_SUCCESS.toString());
        } catch (Exception e) {
            return InternalServerErrorHandler.InternalServerError(e);
        }
    }

    @Override
    public BaseResponse update(CheckoutRequest request) {
        try {
            CheckoutEntity oldDataHeader = checkoutRepository.findByCheckoutNumber(request.getCheckoutNumber());
            if (oldDataHeader == null) {
                return new BaseResponse(HttpStatus.NOT_FOUND.value(), false, ResponseMessagesConst.UPDATE_FAILED.toString());
            }

            oldDataHeader.setUuid(UUID.randomUUID().toString());
            oldDataHeader.setCompanyCode(request.getCompanyCode());
            oldDataHeader.setCheckoutNumber(request.getCheckoutNumber());
            oldDataHeader.setGrossAmount(request.getGrossAmount());
            oldDataHeader.setNetAmount(request.getNetAmount());
            oldDataHeader.setUserCode(request.getUserCode());

            Timestamp dateNow = new Timestamp(new Date().getTime());

            oldDataHeader.setCreatedAt(dateNow);
            oldDataHeader.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
            oldDataHeader.setModifiedAt(dateNow);
            oldDataHeader.setModifiedBy(SecurityContextHolder.getContext().getAuthentication().getName());
            
            List<CheckoutItemEntity> listItem = checkoutItemRepository.findAllByCheckoutNumber(request.getCheckoutNumber());
            checkoutItemRepository.deleteAll(listItem);

            listItem = new ArrayList<>();
            for (CheckoutItemRequest item : request.getListItem()) {
                CheckoutItemEntity dataItem = new CheckoutItemEntity();
                dataItem.setCheckoutNumber(item.getCheckoutNumber());
                dataItem.setLineAmount(item.getLineAmount());
                dataItem.setProductCode(item.getProductCode());
                dataItem.setQuantity(item.getQuantity());
                dataItem.setSellingPrice(item.getSellingPrice());
                
                listItem.add(dataItem);
            }

            checkoutRepository.save(oldDataHeader);
            checkoutItemRepository.saveAll(listItem);

            return new BaseResponse(HttpStatus.CREATED.value(), true, ResponseMessagesConst.UPDATE_SUCCESS.toString());
        } catch (Exception e) {
            return InternalServerErrorHandler.InternalServerError(e);
        }
    }

    @Override
    public BaseResponse findByProductCodeAndUserCode(String productCode, String userCode) {
        try {
            CheckoutResponse dataCheckout = checkoutRepository.findByProductCodeAndUserCode(productCode, userCode);
            if (dataCheckout == null) {
                return new BaseResponse(HttpStatus.NOT_FOUND.value(), false, ResponseMessagesConst.DATA_NOT_FOUND.toString());
            }

            List<CheckoutItemResponse> listItem = checkoutItemRepository.findByCheckoutNumber(dataCheckout.getCheckoutNumber());
            if (listItem == null || listItem.isEmpty()) {
                return new BaseResponse(HttpStatus.NOT_FOUND.value(), false, ResponseMessagesConst.DATA_NOT_FOUND.toString());
            }

            dataCheckout.setListItem(listItem);

            return new BaseResponse(HttpStatus.FOUND.value(), true, ResponseMessagesConst.DATA_FOUND.toString(), dataCheckout);
        } catch (Exception e) {
            return InternalServerErrorHandler.InternalServerError(e);
        }
    }

    @Override
    public BaseResponse findByProductCode(String code) {
        try {
            CheckoutResponse dataCheckout = checkoutRepository.findByProductCode(code);
            if (dataCheckout == null) {
                return new BaseResponse(HttpStatus.NOT_FOUND.value(), false, ResponseMessagesConst.DATA_NOT_FOUND.toString());
            }

            List<CheckoutItemResponse> listItem = checkoutItemRepository.findByCheckoutNumber(dataCheckout.getCheckoutNumber());
            if (listItem == null || listItem.isEmpty()) {
                return new BaseResponse(HttpStatus.NOT_FOUND.value(), false, ResponseMessagesConst.DATA_NOT_FOUND.toString());
            }

            dataCheckout.setListItem(listItem);

            return new BaseResponse(HttpStatus.FOUND.value(), true, ResponseMessagesConst.DATA_FOUND.toString(), dataCheckout);
        } catch (Exception e) {
            return InternalServerErrorHandler.InternalServerError(e);
        }
    }

    @Override
    public BaseResponse deleteByCheckoutNumber(String checkoutNumber) {
        try {
            CheckoutEntity dataCheckout = checkoutRepository.findByCheckoutNumber(checkoutNumber);
            if (dataCheckout == null) {
                return new BaseResponse(HttpStatus.NOT_FOUND.value(), false, ResponseMessagesConst.DATA_NOT_FOUND.toString());
            }

            checkoutRepository.delete(dataCheckout);

            List<CheckoutItemEntity> listItem = checkoutItemRepository.findAllByCheckoutNumber(checkoutNumber);
            checkoutItemRepository.deleteAll(listItem);

            return new BaseResponse(HttpStatus.OK.value(), false, ResponseMessagesConst.DELETE_SUCCESS.toString());
        } catch (Exception e) {
            return InternalServerErrorHandler.InternalServerError(e);
        }
    }

}
