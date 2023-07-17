package com.wanpos.app.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NonNull;

@Data
@NonNull
public class OrderItemRequest {

    @JsonProperty("product_code")
    private String productCode;

    @JsonProperty("quantity")
    private int quantity;

    @JsonProperty("total_price")
    private double totalPrice;

    @JsonProperty("price")
    private double price;

}
