package com.meli.mutant.repository;

import com.meli.mutant.entity.DnaRegister;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DnaRegisterRepository extends JpaRepository<DnaRegister, Long> {


}
