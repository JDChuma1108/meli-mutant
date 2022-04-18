package com.meli.mutant.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class StatsDTO {
    @JsonProperty("count_mutant_dna")
    Integer countMutantDna;
    @JsonProperty("count_human_dna")
    Integer countHumanDna;
    @JsonProperty("ratio")
    Double ratio;
}
