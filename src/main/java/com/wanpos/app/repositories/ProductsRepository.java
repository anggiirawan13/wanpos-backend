package com.wanpos.app.repositories;

import com.wanpos.app.entities.ProductsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductsRepository extends JpaRepository<ProductsEntity, Long> {

    @Query(value = "SELECT * FROM products WHERE uuid = :uuid", nativeQuery = true)
    ProductsEntity getProductByUUID(@Param("uuid") String uuid);

}
