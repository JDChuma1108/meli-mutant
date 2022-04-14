package com.meli.mutant.controller;

import com.meli.mutant.usecase.IMutantUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mutant")
public class MutantRestController {

    private IMutantUseCase iMutantUseCase;

    @PostMapping("/isMutant")
    ResponseEntity<Boolean> isMutant(@RequestBody String[] dna){
        return  new ResponseEntity<>(iMutantUseCase.isMutant(dna), HttpStatus.OK);
    }

}
