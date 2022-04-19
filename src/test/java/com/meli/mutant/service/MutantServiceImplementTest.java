package com.meli.mutant.service;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DisplayName("Test mutant service")
class MutantServiceImplementTest {
    MutantServiceImplement mutantServiceImplement;

    @BeforeEach
    public void before(){
        mutantServiceImplement = spy(MutantServiceImplement.class);
    }

    @Test
    @DisplayName("isMutant must return true when is a mutant")
    void isMutantMustReturnMutant(){
        String[] dna = new String[]{"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
        doNothing().when(mutantServiceImplement).saveDnaRegister(any());

        Boolean isMutant = mutantServiceImplement.isMutant(dna);

        Assert.assertEquals(Boolean.TRUE, isMutant);
    }

    @Test
    @DisplayName("isMutant must return false when is a human")
    void isMutantMustReturnHuman(){
        String[] dna = new String[]{"TTGCGA","CAGTGC","TTATGT","AGAAGG","CCCTTA","TCACTG"};
        doNothing().when(mutantServiceImplement).saveDnaRegister(any());

        Boolean isMutant = mutantServiceImplement.isMutant(dna);

        Assert.assertEquals(Boolean.FALSE, isMutant);
    }

    @Test
    @DisplayName("isMutant must return false when is a bad dimension")
    void isMutantMustReturnBadDimension(){
        String[] dna = new String[]{"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACT"};
        doNothing().when(mutantServiceImplement).saveDnaRegister(any());

        Boolean isMutant = mutantServiceImplement.isMutant(dna);

        Assert.assertEquals(Boolean.FALSE, isMutant);
    }

    @Test
    @DisplayName("isMutant must return false when is a bad element")
    void isMutantMustReturnBadElement(){
        String[] dna = new String[]{"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCAXXX"};
        doNothing().when(mutantServiceImplement).saveDnaRegister(any());

        Boolean isMutant = mutantServiceImplement.isMutant(dna);

        Assert.assertEquals(Boolean.FALSE, isMutant);
    }

    @Test
    @DisplayName("isMutant must return true when is a mutant when is lower case")
    void isMutantMustReturnMutantWhenLowerCase(){
        String[] dna = new String[]{"aTGCGA","CAGTGC","TTATGT","AGAAGG","cCCCTA","TCACTg"};
        doNothing().when(mutantServiceImplement).saveDnaRegister(any());

        Boolean isMutant = mutantServiceImplement.isMutant(dna);

        Assert.assertEquals(Boolean.TRUE, isMutant);
    }

    @Test
    @DisplayName("validateDimension must return true when dimension is NxN")
    void validateDimensionMustReturn(){
        String[] dna = new String[]{"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};

        Boolean validateDimension = mutantServiceImplement.validateDimension(Arrays.stream(dna).collect(Collectors.toList()));

        Assert.assertEquals(Boolean.TRUE, validateDimension);
    }

    @Test
    @DisplayName("getCountSequence must return when has a horizontal sequence")
    void getCountSequenceMustReturnHorizontalSequence(){
        List<String> dnaList = new ArrayList<>(List.of("ATGCGAAAA","CAGTGCAAA","TTATGTAAA","AGAAGGAAA","CCCCTAAAA","TCACTGAAA","AGAAGGAAA","CCCCTAAAA","TCACTGAAA"));
        String row = "CCCCTAAAA";
        Integer sequence = 1;
        Character letter = Character.valueOf('C');
        Integer listPosition = 5;
        Integer rowPosition = 0;

        Integer countSequence = mutantServiceImplement.getCountSequence(dnaList, row, letter, listPosition, rowPosition);

        Assert.assertEquals(sequence, countSequence);
    }
}
