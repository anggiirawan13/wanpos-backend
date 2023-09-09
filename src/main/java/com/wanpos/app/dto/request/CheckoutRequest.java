package com.wanpos.app.dto.request;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NonNull;

@Data
@NonNull
@JsonAutoDetect
public class CheckoutRequest implements Serializable {
    
    @JsonProperty("company_code")
    private String companyCode;

    @JsonProperty("checkout_number")
    private String checkoutNumber;

    @JsonProperty("user_code")
    private String userCode;

    @JsonProperty("gross_amount")
    private double grossAmount;
    
    @JsonProperty("net_amount")
    private double netAmount;

    @JsonProperty("items")
    private List<CheckoutItemRequest> listItem;

}
