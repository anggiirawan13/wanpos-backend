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
    
    @Query("SELECT new com.wanpos.app.dto.response.CheckoutItemResponse(productCode, quantity, sellingPrice, lineAmount) "
        + "FROM CheckoutItemEntity "
        + "WHERE checkoutNumber = :checkoutNumber")
    List<CheckoutItemResponse> findByCheckoutNumber(@Param("checkoutNumber") String checkoutNumber);

}
