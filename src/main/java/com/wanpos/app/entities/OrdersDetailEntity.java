package com.wanpos.app.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders_detail")
public class OrdersDetailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "uuid", nullable = false, length = 64)
    @JsonProperty("uuid")
    private String uuid;

    @Column(name = "product_code", nullable = false, length = 20)
    @JsonProperty("product_code")
    private String productCode;

    @Column(name = "quantity", length = 4)
    @JsonProperty("quantity")
    private int quantity;

    @Column(name = "total_price", nullable = false, length = 11)
    @JsonProperty("total_price")
    private double totalPrice;

    @Column(name = "price", nullable = false, length = 11)
    @JsonProperty("price")
    private double price;

}
