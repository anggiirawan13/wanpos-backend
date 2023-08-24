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
@Table(name = "company")
public class CompanyEntity extends BaseEntity {

    @Column(name = "company_code", unique = true, nullable = false, length = 30)
    @JsonProperty("company_code")
    private String companyCode;

    @Column(name = "company_name", nullable = false, length = 100)
    @JsonProperty("company_name")
    private String companyName;

    @Column(name = "company_address", nullable = false)
    @JsonProperty("company_address")
    private String companyAddress;

}
