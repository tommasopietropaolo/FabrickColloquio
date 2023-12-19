package com.fabrick.bankapp.dto.balanceDto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Balance implements Serializable {

    private Double balance;

    @JsonCreator
    public Balance(@JsonProperty("balance")Double balance) {
        this.balance = balance;
    }


    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
