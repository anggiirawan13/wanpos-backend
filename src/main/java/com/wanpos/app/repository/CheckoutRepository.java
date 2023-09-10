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

    @Query(value = "SELECT header.company_code, header.checkout_number, header.user_code, header.gross_amount, header.net_amount "
    + "FROM checkout AS header "
    + "INNER JOIN (SELECT checkout_number FROM checkout_item WHERE product_code = :productCode) AS item "
    + "ON header.checkout_number = item.checkout_number "
    + "WHERE header.user_code = :userCode", nativeQuery = true)
    CheckoutResponse findByProductCodeAndUserCode(String productCode, String userCode);

    @Query("SELECT new com.wanpos.app.dto.response.CheckoutResponse("
        + "header.companyCode, header.checkoutNumber, header.userCode, header.grossAmount, header.netAmount) "
        + "FROM CheckoutEntity header "
        + "JOIN CheckoutItemEntity itemm "
        + "ON header.checkoutNumber = itemm.checkoutNumber WHERE itemm.productCode = :productCode")
    CheckoutResponse findByProductCode(@Param("productCode")String productCode);
    
    @Query("SELECT new com.wanpos.app.dto.response.CheckoutResponse("
        + "header.companyCode, header.checkoutNumber, header.userCode, header.grossAmount, header.netAmount) "
        + "FROM CheckoutEntity AS header "
        + "INNER JOIN CheckoutItemEntity AS item "
        + "ON header.checkoutNumber = item.checkoutNumber WHERE header.userCode = :code")
    List<CheckoutResponse> findByUserCode(@Param("code") String code);

    CheckoutEntity findByCheckoutNumber(String checkoutNumber);

}
