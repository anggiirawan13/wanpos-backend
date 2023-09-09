package com.wanpos.app.dto.response;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckoutResponse implements Serializable {

    public CheckoutResponse(String companyCode, String checkoutNumber, String userCode, double grossAmount, double netAmount) {
        this.companyCode = companyCode;
        this.checkoutNumber = checkoutNumber;
        this.userCode = userCode;
        this.grossAmount = grossAmount;
        this.netAmount = netAmount;
    }
    
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
    private List<CheckoutItemResponse> listItem;

}
