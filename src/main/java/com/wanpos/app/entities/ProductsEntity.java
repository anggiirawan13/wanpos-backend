package com.wanpos.app.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products")
public class ProductsEntity extends BaseEntity {

    @Column(name = "product_code", unique = true, nullable = false, length = 20)
    private String productCode;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "thumbnail", length = 50)
    private String thumbnail;

    @Column(name = "price", nullable = false, length = 11)
    private double price;

}
