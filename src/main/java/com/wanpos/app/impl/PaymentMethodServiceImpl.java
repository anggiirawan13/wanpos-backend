package com.wanpos.app.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.wanpos.app.dto.response.BaseResponse;
import com.wanpos.app.entity.PaymentMethodEntity;
import com.wanpos.app.repository.PaymentMethodRepository;
import com.wanpos.app.service.PaymentMethodService;
import com.wanpos.constanta.ResponseMessagesConst;
import com.wanpos.handler.InternalServerErrorHandler;

@Service
public class PaymentMethodServiceImpl implements PaymentMethodService {
    
    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    @Override
    public BaseResponse savePaymentMethod(String code, String name) {
        try {
            PaymentMethodEntity dataExist = paymentMethodRepository.findByPaymentMethodCode(code);
            if (dataExist != null) {
                return new BaseResponse(HttpStatus.CONFLICT.value(), false, ResponseMessagesConst.ALREADY_EXIST.toString());
            }

            PaymentMethodEntity newData = new PaymentMethodEntity();
            newData.setUuid(UUID.randomUUID().toString());
            newData.setPaymentMethodCode(code);
            newData.setPaymentMethodName(name);

            Timestamp dateNow = new Timestamp(new Date().getTime());

            newData.setCreatedAt(dateNow);
            newData.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
            newData.setModifiedAt(dateNow);
            newData.setModifiedBy(SecurityContextHolder.getContext().getAuthentication().getName());

            paymentMethodRepository.save(newData);

            return new BaseResponse(HttpStatus.CREATED.value(), true, ResponseMessagesConst.INSERT_SUCCESS.toString());
        } catch (Exception e) {
            return InternalServerErrorHandler.InternalServerError(e);
        }
    }

    @Override
    public BaseResponse getAllPaymentMethod() {
        try {
            List<PaymentMethodEntity> listPaymentMethod = paymentMethodRepository.findAll();
            if (listPaymentMethod == null || listPaymentMethod.isEmpty()) {
                return new BaseResponse(HttpStatus.NOT_FOUND.value(), false, ResponseMessagesConst.DATA_NOT_FOUND.toString());
            }

            return new BaseResponse(HttpStatus.OK.value(), true, ResponseMessagesConst.DATA_FOUND.toString(), listPaymentMethod);
        } catch (Exception e) {
            return InternalServerErrorHandler.InternalServerError(e);
        }
    }

}
