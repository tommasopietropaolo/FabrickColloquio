package com.fabrick.bankapp.dto.transferDto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Creditor implements Serializable {
    //required
    private String name;
    //required
    private Account account;
    private Address address;
    @JsonCreator
    public Creditor(@JsonProperty("name") String name,
                    @JsonProperty("account") Account account,
                    @JsonProperty("address") Address address) {
        this.name = name;
        this.account = account;
        this.address = address;
    }

    //obbligatri
    public Creditor(String name, Account account) {
        this.name = name;
        this.account = account;
    }

    public Creditor() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }


}
