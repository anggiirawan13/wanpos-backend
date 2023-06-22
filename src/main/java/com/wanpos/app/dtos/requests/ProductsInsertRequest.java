package com.wanpos.app.dtos.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NonNull;

import java.util.List;

@Data
@NonNull
public class ProductsInsertRequest {

    @JsonProperty(value = "product_code")
    private String productCode;

    @JsonProperty(value = "product_name")
    private String productName;

    @JsonProperty(value = "thumbnail")
    private String thumbnail;

    @JsonProperty(value = "price")
    private double price;

    @JsonProperty(value = "category_id")
    private Long categoryID;

}
