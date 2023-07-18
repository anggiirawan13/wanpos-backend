package com.wanpos.app.repository;

import com.wanpos.app.entity.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderItemEntity, Long> {

    @Query(value = "SELECT * FROM order_item WHERE uuid = :uuid AND status = 'active'", nativeQuery = true)
    OrderItemEntity findByUUID(@Param("uuid") String uuid);

}
