package com.meli.mutant.usecase;

import com.meli.mutant.dto.StatsDTO;
import com.meli.mutant.entity.DnaRegisterEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IMutantUseCase {
    ResponseEntity<Boolean> isMutant(String[] dna);
    StatsDTO stats();

    List<DnaRegisterEntity> registers();
}
