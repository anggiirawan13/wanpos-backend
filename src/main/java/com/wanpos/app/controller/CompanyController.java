package com.wanpos.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wanpos.app.dto.request.CompanyRequest;
import com.wanpos.app.dto.response.BaseResponse;
import com.wanpos.app.service.CompanyService;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Company")
@CrossOrigin
@RestController
@RequestMapping("company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @PostMapping
    public BaseResponse save(@RequestBody CompanyRequest request) {
        return companyService.save(request);
    }

    @PutMapping
    public BaseResponse update(@RequestBody CompanyRequest request) {
        return companyService.update(request);
    }

    @GetMapping("/{code}")
    public BaseResponse findByCompanyCode(@PathVariable(value = "code") String companyCode) {
        return companyService.findByCompanyCode(companyCode);
    }

    @GetMapping
    public BaseResponse findAll() {
        return companyService.findAll();
    }

}
