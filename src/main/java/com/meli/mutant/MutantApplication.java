package com.meli.mutant;

import com.meli.mutant.entity.DnaRegister;
import com.meli.mutant.repository.DnaRegisterRepository;
import lombok.Builder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
@Builder
public class MutantApplication implements CommandLineRunner {

	private final Log  LOGGER = LogFactory.getLog(MutantApplication.class);

	@Autowired
	private DnaRegisterRepository dnaRegisterRepository;

	public static void main(String[] args) {
		SpringApplication.run(MutantApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	}

	private void save(){
		DnaRegister dnaRegister = DnaRegister.builder().dnaType("01").build();
		dnaRegisterRepository.save(dnaRegister);
		List<DnaRegister> dnaRegisterList = dnaRegisterRepository.findAll();

	}
}
