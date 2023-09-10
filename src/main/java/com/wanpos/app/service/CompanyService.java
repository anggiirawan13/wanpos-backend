package com.wanpos.app.service;

import com.wanpos.app.dto.request.CompanyRequest;
import com.wanpos.app.dto.response.BaseResponse;

public interface CompanyService {

    BaseResponse save(CompanyRequest request);

    BaseResponse findByCompanyCode(String companyCode);
    
}
