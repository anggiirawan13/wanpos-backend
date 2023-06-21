package com.wanpos.app.entities;

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
    private String categoryCode;

    @Column(name = "category_name", nullable = false, length = 20)
    private String categoryName;

}
