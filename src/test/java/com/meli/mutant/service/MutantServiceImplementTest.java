package com.meli.mutant.service;

import com.meli.mutant.MutantApplication;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.sql.Array;

@DisplayName("Test mutant service")
public class MutantServiceImplementTest {
    MutantServiceImplement mutantServiceImplement;

    //@InjectMocks
    //MutantServiceImplement  mutantServiceImplement;

    @Before
    public void before(){
        mutantServiceImplement = new MutantServiceImplement();
    }

    @Test
    public void isMutantMustReturnMutant(){
        String[] dna = new String[]{"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};

        Boolean isMutant = mutantServiceImplement.isMutant(dna);

        Assert.assertEquals(Boolean.TRUE, isMutant);
    }
}
