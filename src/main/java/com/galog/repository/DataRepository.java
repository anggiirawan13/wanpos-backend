package com.galog.repository;

import com.galog.dto.out.DataDTOOut;
import com.galog.entity.DataEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DataRepository extends CrudRepository<DataEntity, Long> {
    @Query(value = "SELECT err_code, info_err FROM data WHERE err_code = :errCode", nativeQuery = true)
    DataDTOOut findByErrorCode(@Param("errCode") String errCode);
}
