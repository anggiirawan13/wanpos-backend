package com.wanpos.app.repositories;

import com.wanpos.app.entities.OrdersDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersDetailRepository extends JpaRepository<OrdersDetailEntity, Long> {

    @Query(value = "SELECT * FROM orders_detail WHERE uuid = :uuid", nativeQuery = true)
    OrdersDetailEntity getOrderDetailByUUID(@Param("uuid") String uuid);

}
