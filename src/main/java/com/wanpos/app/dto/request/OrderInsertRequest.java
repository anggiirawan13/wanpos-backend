package com.wanpos.app.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NonNull;

import java.util.List;

@Data
@NonNull
public class OrderInsertRequest {

    @JsonProperty("username")
    private String username;

    @JsonProperty("sub_total")
    private double subTotal;

    @JsonProperty("total_net")
    private double totalNet;

    @JsonProperty("tax")
    private double tax;

    @JsonProperty("service_charge")
    private double serviceCharge;

    @JsonProperty("item")
    private List<OrderItemRequest> item;

}

