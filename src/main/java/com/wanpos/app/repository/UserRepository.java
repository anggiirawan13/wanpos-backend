package com.wanpos.app.repository;

import com.wanpos.app.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByUuid(String uuid);

    UserEntity findByUsername(String username);

}
