package com.wanpos.app.repositories;

import com.wanpos.app.entities.OrdersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<OrdersEntity, Long> {

    @Query(value = "SELECT * FROM orders WHERE uuid = :uuid", nativeQuery = true)
    OrdersEntity getOrderByUUID(@Param("uuid") String uuid);

    @Query(value = "SELECT * FROM orders WHERE username = :search", nativeQuery = true)
    List<OrdersEntity> getOrdersByUsername(@Param("search") String search);

}
