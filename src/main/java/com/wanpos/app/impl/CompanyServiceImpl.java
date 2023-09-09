package com.wanpos.app.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.security.core.context.SecurityContextHolder;

import com.wanpos.app.dto.request.CompanyRequest;
import com.wanpos.app.dto.response.BaseResponse;
import com.wanpos.app.entity.CompanyEntity;
import com.wanpos.app.repository.CompanyRepository;
import com.wanpos.app.service.CompanyService;
import com.wanpos.constanta.ResponseMessagesConst;
import com.wanpos.handler.InternalServerErrorHandler;
import com.wanpos.helper.NullEmptyChecker;

@Service
public class CompanyServiceImpl implements CompanyService {
    
    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public BaseResponse saveCompany(CompanyRequest request) {
        try {
            CompanyEntity companyCode = companyRepository.findByCompanyCode(request.getCompanyCode());
            if (NullEmptyChecker.isNotNullOrEmpty(companyCode)) {
                return new BaseResponse(HttpStatus.CONFLICT.value(), true, ResponseMessagesConst.ALREADY_EXIST.toString());
            }

            CompanyEntity newData = new CompanyEntity();
            newData.setUuid(UUID.randomUUID().toString());
            newData.setCompanyCode(request.getCompanyCode());
            newData.setCompanyName(request.getCompanyName());
            newData.setCompanyAddress(request.getCompanyAddress());

            Timestamp dateNow = new Timestamp(new Date().getTime());

            newData.setCreatedAt(dateNow);
            newData.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
            newData.setModifiedAt(dateNow);
            newData.setModifiedBy(SecurityContextHolder.getContext().getAuthentication().getName());

            companyRepository.save(newData);

            return new BaseResponse(HttpStatus.CREATED.value(), true, ResponseMessagesConst.INSERT_SUCCESS.toString());
        } catch (Exception e) {
            return InternalServerErrorHandler.InternalServerError(e);
        }
    }

    @Override
    public BaseResponse getCompanyByCompanyCode(String companyCode) {
        try {
            CompanyEntity resultCompany = companyRepository.findByCompanyCode(companyCode);
            if (NullEmptyChecker.isNullOrEmpty(resultCompany)) {
                return new BaseResponse(HttpStatus.NOT_FOUND.value(), false, ResponseMessagesConst.DATA_NOT_FOUND.toString());
            }

            return new BaseResponse(HttpStatus.FOUND.value(), true, ResponseMessagesConst.DATA_FOUND.toString(), resultCompany);
        } catch (Exception e) {
            return InternalServerErrorHandler.InternalServerError(e);
        }
    }

}
