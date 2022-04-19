package com.meli.mutant.controller;

import com.meli.mutant.dto.StatsDTO;
import com.meli.mutant.entity.DnaRegisterEntity;
import com.meli.mutant.usecase.IMutantUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @GetMapping(value = "/stats", produces = MediaType.APPLICATION_JSON_VALUE)
    StatsDTO stats(){
        return iMutantUseCase.stats();
    }

    @GetMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    List<DnaRegisterEntity> registers(){
        return iMutantUseCase.registers();
    }

}
