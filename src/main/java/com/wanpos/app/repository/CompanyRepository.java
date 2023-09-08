package com.wanpos.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wanpos.app.entity.CompanyEntity;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyEntity, Long> {
    
    @Query(value = "SELECT * FROM company WHERE company_code = :companyCode", nativeQuery = true)
    CompanyEntity findByCompanyCode(@Param("companyCode") String companyCode);

}
