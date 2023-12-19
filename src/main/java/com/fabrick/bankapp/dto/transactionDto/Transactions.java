package com.fabrick.bankapp.dto.transactionDto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class Transactions implements Serializable {

    private List<Transaction> list;

    @JsonCreator
    public Transactions(@JsonProperty("list") List<Transaction> list) {
        this.list = list;
    }

    public List<Transaction> getList() {
        return list;
    }

    public void setList(List<Transaction> list) {
        this.list = list;
    }
}
