package com.wanpos.app.repository;

import com.wanpos.app.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    @Query(value = "SELECT * FROM order_header WHERE uuid = :uuid", nativeQuery = true)
    OrderEntity getOrderByUUID(@Param("uuid") String uuid);

    @Query(value = "SELECT * FROM order_header WHERE username = :search", nativeQuery = true)
    List<OrderEntity> getOrderByUsername(@Param("search") String search);

}
