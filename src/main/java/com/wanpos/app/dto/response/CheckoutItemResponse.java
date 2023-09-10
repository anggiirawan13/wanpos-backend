package com.wanpos.app.dto.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckoutItemResponse implements Serializable {

    @JsonProperty("product_code")
    private String productCode;
    
    @JsonProperty("product_name")
    private String productName;

    @JsonProperty("quantity")
    private int quantity;

    @JsonProperty("selling_price")
    private double sellingPrice;

    @JsonProperty("line_amount")
    private double lineAmount;

}
