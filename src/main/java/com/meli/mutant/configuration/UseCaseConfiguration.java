package com.meli.mutant.configuration;

import com.meli.mutant.usecase.IMutantUseCase;
import com.meli.mutant.usecase.MutantUseCaseImplement;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfiguration {

    @Bean
    IMutantUseCase iMutantUseCase(){
        return new MutantUseCaseImplement();
    }
}
