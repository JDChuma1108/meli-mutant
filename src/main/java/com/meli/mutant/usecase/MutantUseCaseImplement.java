package com.meli.mutant.usecase;

import com.meli.mutant.dto.StatsDTO;
import com.meli.mutant.enums.DnaTypeEnum;
import com.meli.mutant.service.IMutantService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RequiredArgsConstructor
public class MutantUseCaseImplement implements IMutantUseCase {

    @Autowired
    IMutantService iMutantService;

    @Override
    public ResponseEntity<Boolean> isMutant(String[] dna) {
        ResponseEntity<Boolean> response = new ResponseEntity<>(false,HttpStatus.FORBIDDEN);
        Boolean isMutant = iMutantService.isMutant(dna);
        if (Boolean.TRUE.equals(isMutant)) response = new ResponseEntity<>(true, HttpStatus.OK);
        return response;
    }

    @Override
    public StatsDTO stats() {
        long countMutantDna = iMutantService.countDnaRegisterByDnaType(DnaTypeEnum.MUTANT.getValue());
        long countHumanDna = iMutantService.countDnaRegisterByDnaType(DnaTypeEnum.HUMAN.getValue());
        double ratio = (double) countMutantDna/countHumanDna;


        return StatsDTO.builder()
                .countMutantDna((int) countMutantDna)
                .countHumanDna((int) countHumanDna)
                .ratio(ratio)
                .build();
    }
}
