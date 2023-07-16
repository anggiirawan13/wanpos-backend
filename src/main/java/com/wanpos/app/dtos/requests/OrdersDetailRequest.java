package com.wanpos.app.dtos.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NonNull;

@Data
@NonNull
public class OrdersDetailRequest {

    @JsonProperty("product_code")
    private String productCode;

    @JsonProperty("quantity")
    private int quantity;

    @JsonProperty("total_price")
    private double totalPrice;

    @JsonProperty("price")
    private double price;

}
