package com.wanpos.app.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "order")
public class OrderEntity extends BaseEntity {

    @Column(name = "username", unique = true, nullable = false, length = 50)
    @JsonProperty("username")
    private String username;

    @Column(name = "sub_total", nullable = false, length = 11)
    @JsonProperty("sub_total")
    private double subTotal;

    @Column(name = "total_net", nullable = false, length = 11)
    @JsonProperty("total_net")
    private double totalNet;

    @Column(name = "tax", nullable = false, length = 11)
    @JsonProperty("tax")
    private double tax;

    @Column(name = "service_charge", nullable = false, length = 11)
    @JsonProperty("service_charge")
    private double serviceCharge;

}
