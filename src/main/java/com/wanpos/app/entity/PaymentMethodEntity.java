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
@Table(name = "payment_method")
public class PaymentMethodEntity extends BaseEntity {

    @Column(name = "payment_method_code", unique = true, nullable = false, length = 30)
    @JsonProperty("payment_method_code")
    private String paymentMethodCode;

    @Column(name = "payment_method_name", nullable = false, length = 100)
    @JsonProperty("payment_method_name")
    private String paymentMethodName;

}
