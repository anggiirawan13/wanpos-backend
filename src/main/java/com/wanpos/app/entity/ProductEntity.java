package com.wanpos.app.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product")
public class ProductEntity extends BaseEntity {

    @Column(name = "product_code", unique = true, nullable = false, length = 20)
    @JsonProperty("product_code")
    private String productCode;

    @Column(name = "product_name", nullable = false)
    @JsonProperty("product_name")
    private String productName;

    @Column(name = "selling_price", nullable = false, length = 11)
    @JsonProperty("selling_price")
    private double sellingPrice;

    @Column(name = "buying_price", nullable = false, length = 11)
    @JsonProperty("buying_price")
    private double buyingPrice;

    @Column(name = "stock", nullable = false, length = 4)
    @JsonProperty("stock")
    private int stock;

}
