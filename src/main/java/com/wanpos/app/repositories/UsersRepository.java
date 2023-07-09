package com.wanpos.app.repositories;

import com.wanpos.app.entities.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<UsersEntity, Long> {

    @Query(value = "SELECT * FROM users WHERE uuid = :uuid", nativeQuery = true)
    UsersEntity findByUUID(@Param("uuid") String uuid);

    @Query(value = "SELECT * FROM users WHERE username = :username", nativeQuery = true)
    UsersEntity findByUsername(@Param("username") String username);

}
