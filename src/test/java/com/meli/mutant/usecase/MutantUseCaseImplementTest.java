package com.meli.mutant.usecase;


import com.meli.mutant.dto.StatsDTO;
import com.meli.mutant.enums.DnaTypeEnum;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DisplayName("Test mutant use case")
@RunWith(MockitoJUnitRunner.class)
class MutantUseCaseImplementTest {
    MutantUseCaseImplement mutantUseCaseImplement;

    @BeforeEach
    public void before(){
        mutantUseCaseImplement = spy(MutantUseCaseImplement.class);
    }

    @Test
    @DisplayName("isMutant must return true when is a mutant")
    void isMutantMustResponseMutant(){
        String[] dna = new String[]{"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
        ResponseEntity<Boolean> response = new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
        doReturn(Boolean.TRUE).when(mutantUseCaseImplement).isMutantDna(any());

        ResponseEntity<Boolean> isMutant = mutantUseCaseImplement.isMutant(dna);

        Assert.assertEquals(response, isMutant);
    }

    @Test
    @DisplayName("isMutant must return false when is a human")
    void isMutantMustResponseHuman(){
        String[] dna = new String[]{"TTGCGA","CAGTGC","TTATGT","AGAAGG","CCCTTA","TCACTG"};
        ResponseEntity<Boolean> response = new ResponseEntity<>(Boolean.FALSE, HttpStatus.FORBIDDEN);
        doReturn(Boolean.FALSE).when(mutantUseCaseImplement).isMutantDna(any());

        ResponseEntity<Boolean> isMutant = mutantUseCaseImplement.isMutant(dna);

        Assert.assertEquals(response, isMutant);
    }

    @Test
    @DisplayName("stats must return dto with dna register data")
    void statsMust(){
        StatsDTO statsDTO = StatsDTO.builder()
                .countHumanDna(100)
                .countMutantDna(40)
                .ratio(0.4)
                .build();
        doReturn(100L).when(mutantUseCaseImplement).getCountDnaRegister(DnaTypeEnum.HUMAN.getValue());
        doReturn(40L).when(mutantUseCaseImplement).getCountDnaRegister(DnaTypeEnum.MUTANT.getValue());

        StatsDTO response = mutantUseCaseImplement.stats();

        Assert.assertEquals(statsDTO.getCountHumanDna(), response.getCountHumanDna());
        Assert.assertEquals(statsDTO.getCountMutantDna(), response.getCountMutantDna());
        Assert.assertEquals(statsDTO.getRatio(), response.getRatio());
    }
}
