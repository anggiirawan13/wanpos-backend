package com.wanpos.app.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "categories")
public class CategoriesEntity extends BaseEntity {

    @Column(name = "category_code", unique = true, nullable = false, length = 20)
    @JsonProperty("category_code")
    private String categoryCode;

    @Column(name = "category_name", nullable = false, length = 20)
    @JsonProperty("category_name")
    private String categoryName;

}
