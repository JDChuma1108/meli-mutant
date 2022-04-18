package com.meli.mutant.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "dna_register")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DnaRegisterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id", nullable = false, unique = true)
    private Long id;

    private LocalDateTime created;
    private LocalDateTime updated;
    private String dnaType;
    private String dna;
}
