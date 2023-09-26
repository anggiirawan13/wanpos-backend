package com.wanpos.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wanpos.app.entity.ProductEntity;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    
    ProductEntity findByProductCode(String code);

    List<ProductEntity> findAllByStatus(String status);

    @Query(value = "SELECT IFNULL(stock, 0) AS stock FROM product WHERE product_code = :code", nativeQuery = true)
    int findStockByProductCode(@Param("code") String code);

}
