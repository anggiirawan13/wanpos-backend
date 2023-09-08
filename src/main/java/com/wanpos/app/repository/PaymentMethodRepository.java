package com.wanpos.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wanpos.app.entity.PaymentMethodEntity;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethodEntity, Long> {
    
    @Query(value = "SELECT * FROM payment_method WHERE payment_method_code = :code", nativeQuery = true)
    PaymentMethodEntity findByPaymentMethodCode(@Param("code") String code);

}
