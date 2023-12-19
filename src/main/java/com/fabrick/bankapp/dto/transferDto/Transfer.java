package com.fabrick.bankapp.dto.transferDto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class Transfer implements Serializable {
    private String moneyTransferId;


    /* possible data to use from the service response:
    private String status;
    private String direction;
    private Creditor creditor;
    private Debtor debtor;
    private String cro;
    private String uri;
    private String trn;
    private String description;
    private LocalDateTime createdDatetime;
    private LocalDateTime accountedDatetime;
    private String debtorValueDate;
    private String creditorValueDate;
    private Amount amount;
    private boolean isUrgent;
    private boolean isInstant;
    private String feeType;
    private String feeAccountId;
    private List<Fee> fees;
    private boolean hasTaxRelief;*/

    @JsonCreator
    public Transfer(@JsonProperty("moneyTransferId") String moneyTransferId) {
        this.moneyTransferId = moneyTransferId;
    }

    public String getMoneyTransferId() {
        return moneyTransferId;
    }

    public void setMoneyTransferId(String moneyTransferId) {
        this.moneyTransferId = moneyTransferId;
    }
}
