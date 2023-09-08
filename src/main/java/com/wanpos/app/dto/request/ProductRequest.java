package com.wanpos.app.dto.request;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NonNull;

@Data
@NonNull
@JsonAutoDetect
public class ProductRequest implements Serializable {
    
    @JsonProperty("product_code")
    private String productCode;

    @JsonProperty("product_name")
    private String productName;

    @JsonProperty("selling_price")
    private double sellingPrice;

    @JsonProperty("buying_price")
    private double buyingPrice;

    @JsonProperty("stock")
    private int stock;

}
