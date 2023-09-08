package com.wanpos.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wanpos.app.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    
    @Query(value = "SELECT * FROM product WHERE product_code = :code", nativeQuery = true)
    ProductEntity findProductByProductCode(@Param("code") String code);

}
