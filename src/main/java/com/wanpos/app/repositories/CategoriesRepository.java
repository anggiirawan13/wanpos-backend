package com.wanpos.app.repositories;

import com.wanpos.app.entities.CategoriesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoriesRepository extends JpaRepository<CategoriesEntity, Long> {

    @Query(value = "SELECT * FROM categories WHERE uuid = :uuid", nativeQuery = true)
    CategoriesEntity getCategoryByUUID(@Param("uuid") String uuid);

    @Query(value = "SELECT * FROM categories WHERE category_code = :category_code", nativeQuery = true)
    CategoriesEntity getCategoryByCategoryCode(@Param("category_code") String category_code);

}
