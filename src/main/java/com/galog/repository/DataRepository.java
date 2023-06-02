package com.galog.repository;

import com.galog.dto.response.DataResponse;
import com.galog.entity.DataEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DataRepository extends CrudRepository<DataEntity, Long> {
    @Query(value = "SELECT error_code, desc_error FROM data WHERE error_code = :errorCode", nativeQuery = true)
    DataResponse findByErrorCode(@Param("errorCode") String errorCode);
}
