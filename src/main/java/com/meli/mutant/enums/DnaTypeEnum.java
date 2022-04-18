package com.meli.mutant.enums;

public enum DnaTypeEnum {
    HUMAN("Human", "01"), MUTANT("Mutant", "02");

    private final String key;
    private final String value;

    DnaTypeEnum(String key, String value){
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
