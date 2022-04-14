package com.meli.mutant.service;

import com.meli.mutant.entity.DnaRegister;
//import com.meli.mutant.repository.DnaRegisterRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MutantService {
    private final Log LOGGER = LogFactory.getLog(MutantService.class);

    @Autowired
    //private DnaRegisterRepository dnaRegisterRepository;



    @Transactional
    public void saveTransactional(List<DnaRegister> dnaRegisterList){
        dnaRegisterList.stream()
                .peek(dnaRegister -> LOGGER.info("Insert Dna Register" + dnaRegister));
                //.forEach(dnaRegisterRepository::save);
    }
}
