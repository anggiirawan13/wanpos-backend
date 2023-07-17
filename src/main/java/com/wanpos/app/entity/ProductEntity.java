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

    @Column(name = "thumbnail", length = 50)
    @JsonProperty("thumbnail")
    private String thumbnail;

    @Column(name = "price", nullable = false, length = 11)
    @JsonProperty("price")
    private double price;

    @Column(name = "stock", nullable = false, length = 4)
    @JsonProperty("stock")
    private int stock;

    @Column(name = "category_id", nullable = false)
    @JsonProperty("category_id")
    private Long categoryID;

}
