package com.meli.mutant.service;

import com.meli.mutant.entity.DnaRegisterEntity;

public interface IMutantService {
    boolean isMutant(String[] dna);
    void saveDnaRegister(DnaRegisterEntity dnaRegister);
    long countDnaRegisterByDnaType(String dnaType);
}
