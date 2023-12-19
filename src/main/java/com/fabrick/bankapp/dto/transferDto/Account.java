package com.fabrick.bankapp.dto.transferDto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Account implements Serializable {
    //required
    private String accountCode;
    private String bicCode;

    @JsonCreator
    public Account(@JsonProperty("accountCode") String accountCode,
                   @JsonProperty("bicCode") String bicCode) {
        this.accountCode = accountCode;
        this.bicCode = bicCode;
    }


    public String getAccountCode() {
        return accountCode;
    }

    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

    public String getBicCode() {
        return bicCode;
    }

    public void setBicCode(String bicCode) {
        this.bicCode = bicCode;
    }


}
