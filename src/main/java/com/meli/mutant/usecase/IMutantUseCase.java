package com.meli.mutant.usecase;

import com.meli.mutant.dto.StatsDTO;
import org.springframework.http.ResponseEntity;

public interface IMutantUseCase {
    ResponseEntity<Boolean> isMutant(String[] dna);
    StatsDTO stats();
}
