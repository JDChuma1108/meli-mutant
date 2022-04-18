package com.meli.mutant;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DisplayName("Test mutant application")
class MutantApplicationTests {

	@InjectMocks
	MutantApplication main;

	@BeforeEach
	public void setUp(){
		MockitoAnnotations.openMocks(this);
	}

	@Test
	@DisplayName("Simular carga del application context")
	void contextLoads() {
		main.main(new String[] {});
	}

}
