package com.fabrick.bankapp.dto.transactionDto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class TransactionType implements Serializable {
    private String enumeration;
    private String value;

    @JsonCreator
    public TransactionType(@JsonProperty("enumeration") String enumeration,
                           @JsonProperty("value") String value) {
        this.enumeration = enumeration;
        this.value = value;
    }

    public String getEnumeration() {
        return enumeration;
    }

    public void setEnumeration(String enumeration) {
        this.enumeration = enumeration;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

