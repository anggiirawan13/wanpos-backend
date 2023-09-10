package com.wanpos.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wanpos.app.dto.response.CheckoutItemResponse;
import com.wanpos.app.entity.CheckoutItemEntity;

@Repository
public interface CheckoutItemRepository extends JpaRepository<CheckoutItemEntity, Long> {
    
    @Query("SELECT new com.wanpos.app.dto.response.CheckoutItemResponse(co.productCode, prod.productName, co.quantity, co.sellingPrice, co.lineAmount) "
        + "FROM CheckoutItemEntity AS co "
        + "INNER JOIN ProductEntity AS prod "
        + "ON co.productCode = prod.productCode "
        + "WHERE co.checkoutNumber = :checkoutNumber")
    List<CheckoutItemResponse> findByCheckoutNumber(@Param("checkoutNumber") String checkoutNumber);

    List<CheckoutItemEntity> findAllByCheckoutNumber(String checkoutNumber);

}
