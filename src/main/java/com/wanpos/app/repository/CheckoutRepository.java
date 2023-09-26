package com.wanpos.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wanpos.app.dto.response.CheckoutResponse;
import com.wanpos.app.entity.CheckoutEntity;

@Repository
public interface CheckoutRepository extends JpaRepository<CheckoutEntity, Long> {

    @Query("SELECT new com.wanpos.app.dto.response.CheckoutResponse("
        + "header.companyCode, header.checkoutNumber, header.userCode, header.grossAmount, header.netAmount) "
        + "FROM CheckoutEntity AS header "
        + "INNER JOIN CheckoutItemEntity AS item "
        + "ON header.checkoutNumber = item.checkoutNumber "
        + "WHERE item.productCode = :productCode "
        + "AND header.userCode = :userCode")
    CheckoutResponse findByProductCodeAndUserCode(@Param("productCode") String productCode, @Param("userCode") String userCode);

    @Query("SELECT new com.wanpos.app.dto.response.CheckoutResponse("
        + "header.companyCode, header.checkoutNumber, header.userCode, header.grossAmount, header.netAmount) "
        + "FROM CheckoutEntity AS header "
        + "INNER JOIN CheckoutItemEntity AS item "
        + "ON header.checkoutNumber = item.checkoutNumber WHERE item.productCode = :code")
    CheckoutResponse findByProductCode(@Param("code") String code);
    
    @Query("SELECT new com.wanpos.app.dto.response.CheckoutResponse("
        + "header.companyCode, header.checkoutNumber, header.userCode, header.grossAmount, header.netAmount) "
        + "FROM CheckoutEntity AS header "
        + "INNER JOIN CheckoutItemEntity AS item "
        + "ON header.checkoutNumber = item.checkoutNumber WHERE header.userCode = :code")
    List<CheckoutResponse> findByUserCode(@Param("code") String code);

    CheckoutEntity findByCheckoutNumber(String checkoutNumber);

}
