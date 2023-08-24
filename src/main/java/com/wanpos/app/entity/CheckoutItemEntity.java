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
@Table(name = "checkout_item")
public class CheckoutItemEntity extends BaseEntity {

    @Column(name = "checkout_number", nullable = false, length = 20)
    @JsonProperty("checkout_number")
    private String checkoutNumber;

    @Column(name = "product_code", nullable = false, length = 20)
    @JsonProperty("product_code")
    private String productCode;

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
