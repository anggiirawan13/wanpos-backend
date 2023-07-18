package com.wanpos.app.repository;

import com.wanpos.app.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    @Query(value = "SELECT * FROM product WHERE uuid = :uuid AND status = 'active'", nativeQuery = true)
    ProductEntity findByUUID(@Param("uuid") String uuid);

    @Query(value = "SELECT * FROM product WHERE product_code LIKE %:search% OR product_name LIKE %:search% AND status = 'active'", nativeQuery = true)
    List<ProductEntity> findByProductCodeOrName(@Param("search") String search);

}
