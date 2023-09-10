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
@Table(name = "checkout")
public class CheckoutEntity extends BaseEntity {

    @Column(name = "company_code", nullable = false, length = 100)
    @JsonProperty("company_code")
    private String companyCode;

    @Column(name = "checkout_number", unique = true, nullable = false, length = 20)
    @JsonProperty("checkout_number")
    private String checkoutNumber;

    @Column(name = "user_code", nullable = false, length = 20)
    @JsonProperty("user_code")
    private String userCode;

    @Column(name = "gross_amount", nullable = false, length = 11)
    @JsonProperty("gross_amount")
    private double grossAmount;
    
    @Column(name = "net_amount", nullable = false, length = 11)
    @JsonProperty("net_amount")
    private double netAmount;

}
