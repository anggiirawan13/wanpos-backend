package com.wanpos.app.dto.request;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NonNull;

@Data
@NonNull
@JsonAutoDetect
public class CompanyRequest implements Serializable {
    
    @JsonProperty(value = "company_code")
    private String companyCode;

    @JsonProperty(value = "company_name")
    private String companyName;

    @JsonProperty(value = "company_address")
    private String companyAddress;

}
