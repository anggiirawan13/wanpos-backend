package com.wanpos.app.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "order_item")
public class OrderItemEntity extends BaseEntity {

    @Column(name = "company_code", nullable = false, length = 30)
    @JsonProperty("company_code")
    private String companyCode;

    @Column(name = "order_number", nullable = false, length = 20)
    @JsonProperty("order_number")
    private String orderNumber;

    @Column(name = "quantity", nullable = false, length = 11)
    @JsonProperty("quantity")
    private int quantity;

    @Column(name = "selling_price", nullable = false, length = 11)
    @JsonProperty("selling_price")
    private double sellingPrice;

    @Column(name = "line_amount", nullable = false, length = 11)
    @JsonProperty("line_amount")
    private double lineAmount;

}
