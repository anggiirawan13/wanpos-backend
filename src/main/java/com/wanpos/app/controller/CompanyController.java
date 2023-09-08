package com.wanpos.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wanpos.app.dto.request.CompanyRequest;
import com.wanpos.app.dto.response.BaseResponse;
import com.wanpos.app.impl.CompanyServiceImpl;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Company")
@CrossOrigin
@RestController
@RequestMapping("company")
public class CompanyController {
    
    @Autowired
    private CompanyServiceImpl companyServiceImpl;

    @PostMapping
    public BaseResponse saveCompany(@RequestBody CompanyRequest request) {
        return companyServiceImpl.saveCompany(request);
    }

    @GetMapping("/{code}")
    public BaseResponse getCompanyByCompanyCode(@PathVariable(value = "code") String companyCode) {
        return companyServiceImpl.getCompanyByCompanyCode(companyCode);
    }

}
