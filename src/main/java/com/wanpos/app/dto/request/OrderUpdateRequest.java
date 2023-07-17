package com.wanpos.app.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NonNull;

@Data
@NonNull
public class OrderUpdateRequest {

    @JsonProperty(value = "uuid")
    private String uuid;

    @JsonProperty("username")
    private String username;

    @JsonProperty("product_code")
    private String productCode;

    @JsonProperty("quantity")
    private int quantity;

    @JsonProperty("total_price")
    private double totalPrice;

    @JsonProperty("status")
    private String status;

}
