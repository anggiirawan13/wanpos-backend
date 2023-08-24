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
@Table(name = "order_header")
public class OrderEntity extends BaseEntity {

    @Column(name = "company_code", nullable = false, length = 30)
    @JsonProperty("company_code")
    private String companyCode;

    @Column(name = "order_number", unique = true, nullable = false, length = 20)
    @JsonProperty("order_number")
    private String orderNumber;

    @Column(name = "user_code", nullable = false, length = 20)
    @JsonProperty("user_code")
    private String userCode;

    @Column(name = "gross_amount", nullable = false, length = 11)
    @JsonProperty("gross_amount")
    private double grossAmount;

    @Column(name = "net_amount", nullable = false, length = 11)
    @JsonProperty("net_amount")
    private double netAmount;

    @Column(name = "percent_tax", nullable = false, length = 11)
    @JsonProperty("percent_tax")
    private int percentTax;

    @Column(name = "percent_value", nullable = false, length = 11)
    @JsonProperty("percent_value")
    private double percentValue;

    @Column(name = "payment_method_code", nullable = false, length = 20)
    @JsonProperty("payment_method_code")
    private String paymentMethodCode;

}
