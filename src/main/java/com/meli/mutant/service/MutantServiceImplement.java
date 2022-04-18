package com.meli.mutant.service;

import com.meli.mutant.entity.DnaRegisterEntity;
import com.meli.mutant.enums.DnaTypeEnum;
import com.meli.mutant.repository.DnaRegisterRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.meli.mutant.constants.Constants.*;

@Service
public class MutantServiceImplement implements IMutantService{
    private final Log logger = LogFactory.getLog(MutantServiceImplement.class);

    @Autowired
    private DnaRegisterRepository dnaRegisterRepository;

    /**
     *
     * @param dna Array con las cadenas de ADN
     * @return isMutant Validación si es mutante o humano
     */
    @Override
    public boolean isMutant(String[] dna) {
        Boolean isMutant = false;
        Integer totalSequence = 0;
        String dnaType = DnaTypeEnum.HUMAN.getValue();

        //Se convierte el array a lista
        List<String> dnaList = Arrays.stream(dna).collect(Collectors.toList());

        //Si hay alguna secuencia con una dimensión incorrecta se retorna false
        if(Boolean.FALSE.equals(validateDimension(dnaList))) return isMutant;

        //Si hay algún elemento incorrecto se retorna false
        if(Boolean.FALSE.equals(validateElements(dnaList))) return isMutant;

        //Se convierten las secuencias a mayúsculas
        dnaList.forEach(String::toUpperCase);

        //Se recorre la lista para encontrar las secuencias
        for (Integer listPosition = 0; listPosition < dnaList.size(); listPosition++) {
            String row = dnaList.get(listPosition).toUpperCase();
            for (Integer rowPosition = 0; rowPosition < row.length(); rowPosition++) {
                Character letter = row.charAt(rowPosition);

                Integer countSequence = getCountSequence(dnaList, row, letter, listPosition, rowPosition);
                totalSequence += countSequence;
            }
        }

        //Si se encuentran 2 o más secuencias es un mutante
        if(totalSequence >= COUNT_SEQUENCE){
            isMutant = true;
            dnaType = DnaTypeEnum.MUTANT.getValue();
        }

        //Se guarda en base de datos el ADN verificado
        saveDnaRegister(DnaRegisterEntity.builder()
                .created(LocalDateTime.now())
                .updated(LocalDateTime.now())
                .dnaType(dnaType)
                .dna(dnaList.toString())
                .build());

        logger.info("Registro de ADN guardado correctamente!");

        return isMutant;
    }

    /**
     *
     * @param dnaRegister Entidad de registro de ADN verificado
     */
    @Override
    public void saveDnaRegister(DnaRegisterEntity dnaRegister) {
        dnaRegisterRepository.save(dnaRegister);
    }

    /**
     *
     * @param dnaType Tipo de ADN
     * @return Cantidad de registros de ADN que existen para el tipo de ADN
     */
    @Override
    public long countDnaRegisterByDnaType(String dnaType) {
        return dnaRegisterRepository.countByDnaType(dnaType);
    }

    /**
     *
     * @param dnaList Lista con cadenas de ADN
     * @param row Cadena de ADN sobre la que se están calculando las secuencias
     * @param letter Letra sobre la que se está iterando
     * @param listPosition Posición dentro de la lista de cadenas de ADN
     * @param rowPosition Posición dentro de la cadena de ADN que se está iterando
     * @return sequenceCount Número de secuencias encontradas
     */
    public Integer getCountSequence(List<String> dnaList, String row, char letter, int listPosition, int rowPosition){
        Integer sequenceCount = 0;
        Integer horizontalPosition = row.length() - rowPosition;
        Integer verticalPosition = dnaList.size() - listPosition;

        if(horizontalPosition >= SEQUENCE_ELEMENTS &&
                letter == row.charAt(rowPosition+1) &&
                letter == row.charAt(rowPosition+2) &&
                letter == row.charAt(rowPosition+3)){
            sequenceCount += 1;
            if(sequenceCount >= COUNT_SEQUENCE) return sequenceCount;
        }

        if(verticalPosition >= SEQUENCE_ELEMENTS &&
                letter == dnaList.get(listPosition+1).charAt(rowPosition) &&
                letter == dnaList.get(listPosition+2).charAt(rowPosition) &&
                letter == dnaList.get(listPosition+3).charAt(rowPosition)){
            sequenceCount += 1;
            if(sequenceCount >= COUNT_SEQUENCE) return sequenceCount;
        }

        if(verticalPosition >= SEQUENCE_ELEMENTS &&
                horizontalPosition >= SEQUENCE_ELEMENTS &&
                letter == dnaList.get(listPosition+1).charAt(rowPosition+1) &&
                letter == dnaList.get(listPosition+2).charAt(rowPosition+2) &&
                letter == dnaList.get(listPosition+3).charAt(rowPosition+3)){
            sequenceCount += 1;
            if(sequenceCount >= COUNT_SEQUENCE) return sequenceCount;
        }

        return sequenceCount;
    }

    /**
     *
     * @param dnaList Lista con cadenas de ADN
     * @return validateDimension Valida si las dimensiones de la matriz son NXN
     */
    public Boolean validateDimension(List<String> dnaList){
        Boolean validateDimension = true;

        //Se recorren las secuencias de ADN para validar las dimensiones de la matriz
        List<String> dnaListBadDimension = dnaList.stream()
                .filter(row -> row.length() != dnaList.size())
                .collect(Collectors.toList());

        if(!dnaListBadDimension.isEmpty()) validateDimension = false;

        return validateDimension;

    }

    /**
     *
     * @param dnaList Lista con cadenas de ADN
     * @return validateElements Valida que las secuencias solo tengan las letras A, T, C ó G
     */
    public Boolean validateElements(List<String> dnaList){
        Boolean validateElements = true;
        String validateLetters = VALIDATE_LETTERS;
        List<String> badElementsList = new ArrayList<>();

        dnaList.forEach(row -> row.chars().forEach(letter -> {
            String letterString = String.valueOf((char) letter);
            if(!validateLetters.contains(letterString)) badElementsList.add(letterString);
        }));

        if(!badElementsList.isEmpty()) validateElements = false;

        return validateElements;
    }
}
