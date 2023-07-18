package com.wanpos.app.repository;

import com.wanpos.app.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query(value = "SELECT * FROM user WHERE uuid = :uuid AND status = 'active'", nativeQuery = true)
    UserEntity findByUUID(@Param("uuid") String uuid);

    @Query(value = "SELECT * FROM user WHERE username = :username AND status = 'active'", nativeQuery = true)
    UserEntity findByUsername(@Param("username") String username);

}
