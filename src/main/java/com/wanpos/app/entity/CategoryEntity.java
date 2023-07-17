package com.wanpos.app.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "category")
public class CategoryEntity extends BaseEntity {

    @Column(name = "category_code", unique = true, nullable = false, length = 20)
    @JsonProperty("category_code")
    private String categoryCode;

    @Column(name = "category_name", nullable = false, length = 20)
    @JsonProperty("category_name")
    private String categoryName;

}
