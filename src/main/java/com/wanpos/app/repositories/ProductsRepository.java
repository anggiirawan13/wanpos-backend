package com.wanpos.app.repositories;

import com.wanpos.app.entities.ProductsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsRepository extends JpaRepository<ProductsEntity, Long> {

    @Query(value = "SELECT * FROM products WHERE uuid = :uuid", nativeQuery = true)
    ProductsEntity getProductByUUID(@Param("uuid") String uuid);

    @Query(value = "SELECT * FROM products WHERE product_code LIKE %:search% OR product_name LIKE %:search%", nativeQuery = true)
    List<ProductsEntity> getProductByProductCodeOrName(@Param("search") String search);

}
