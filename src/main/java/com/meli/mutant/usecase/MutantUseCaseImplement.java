package com.meli.mutant.usecase;

import com.meli.mutant.dto.StatsDTO;
import com.meli.mutant.entity.DnaRegisterEntity;
import com.meli.mutant.enums.DnaTypeEnum;
import com.meli.mutant.service.IMutantService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RequiredArgsConstructor
public class MutantUseCaseImplement implements IMutantUseCase {
    @Autowired
    IMutantService iMutantService;

    /**
     *
     * @param dna Lista con cadenas de ADN
     * @return response Respuesta si es mutante o humano con su respectivo estado
     */
    @Override
    public ResponseEntity<Boolean> isMutant(String[] dna) {
        ResponseEntity<Boolean> response = new ResponseEntity<>(false,HttpStatus.FORBIDDEN);
        Boolean isMutant = isMutantDna(dna);
        if (Boolean.TRUE.equals(isMutant)) response = new ResponseEntity<>(true, HttpStatus.OK);
        return response;
    }

    /**
     *
     * @return Dto con datos de los registros verificados de ADN
     */
    @Override
    public StatsDTO stats() {
        long countMutantDna = getCountDnaRegister(DnaTypeEnum.MUTANT.getValue());
        long countHumanDna = getCountDnaRegister(DnaTypeEnum.HUMAN.getValue());
        double ratio = (double) countMutantDna/countHumanDna;


        return StatsDTO.builder()
                .countMutantDna((int) countMutantDna)
                .countHumanDna((int) countHumanDna)
                .ratio(ratio)
                .build();
    }

    /**
     *
     * @return Lista de la entidad de registros de ADN guardados en base de datos
     */
    @Override
    public List<DnaRegisterEntity> registers() {
        return getRegisters();
    }

    public Boolean isMutantDna(String[] dna){
        return iMutantService.isMutant(dna);
    }

    public long getCountDnaRegister(String dnaType){
        return iMutantService.countDnaRegisterByDnaType(dnaType);
    }

    public List<DnaRegisterEntity> getRegisters(){
        return iMutantService.getRegisters();
    }
}
