package com.wanpos.app.dto.request;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NonNull;

@Data
@NonNull
@JsonAutoDetect
public class CheckoutItemRequest implements Serializable {
    
    @JsonProperty("checkout_number")
    private String checkoutNumber;

    @JsonProperty("product_code")
    private String productCode;

    @JsonProperty("quantity")
    private int quantity;

    @JsonProperty("selling_price")
    private double sellingPrice;

    @JsonProperty("line_amount")
    private double lineAmount;

}
