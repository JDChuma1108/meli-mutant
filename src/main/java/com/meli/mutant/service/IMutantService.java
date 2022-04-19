package com.meli.mutant.service;

import com.meli.mutant.entity.DnaRegisterEntity;

import java.util.List;

public interface IMutantService {
    boolean isMutant(String[] dna);
    void saveDnaRegister(DnaRegisterEntity dnaRegister);
    long countDnaRegisterByDnaType(String dnaType);

    List<DnaRegisterEntity> getRegisters();
}
