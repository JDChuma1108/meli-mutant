package com.meli.mutant.controller;

import com.meli.mutant.dto.StatsDTO;
import com.meli.mutant.usecase.IMutantUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class MutantRestController {

    @Autowired
    private IMutantUseCase iMutantUseCase;

    @PostMapping(value = "/mutant",consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Boolean> isMutant(@RequestBody Map<String, String[]> dna){
        return iMutantUseCase.isMutant(dna.get("dna"));
    }

    @PostMapping(value = "/stats", produces = MediaType.APPLICATION_JSON_VALUE)
    StatsDTO stats(){
        return iMutantUseCase.stats();
    }

}
