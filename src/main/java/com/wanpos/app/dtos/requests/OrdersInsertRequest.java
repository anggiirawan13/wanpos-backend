package com.wanpos.app.dtos.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NonNull;

import javax.persistence.Column;
import java.util.List;

@Data
@NonNull
public class OrdersInsertRequest {

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

    @JsonProperty("details")
    private List<OrdersDetailRequest> details;

}

