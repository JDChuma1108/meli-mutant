package com.meli.mutant.repository;

import com.meli.mutant.entity.DnaRegisterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DnaRegisterRepository extends JpaRepository<DnaRegisterEntity, Long> {
    @Query("SELECT count(d) FROM DnaRegisterEntity d WHERE d.dnaType=:dnaType")
    Integer findCountByDnaType(@Param("dnaType") String dnaType);

    long countByDnaType(String dnaType);

}
