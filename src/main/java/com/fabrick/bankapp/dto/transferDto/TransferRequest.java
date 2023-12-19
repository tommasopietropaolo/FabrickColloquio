package com.fabrick.bankapp.dto.transferDto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class TransferRequest implements Serializable {

    //required
    private Creditor creditor;
    private String executionDate;
    private String uri;
    //required
    private String description;
    //required
    private double amount;
    //required
    private String currency;
    private boolean isUrgent;
    private boolean isInstant;
    private String feeType;
    private String feeAccountId;
    private TaxRelief taxRelief;

    @JsonCreator
    public TransferRequest(@JsonProperty("creditor") Creditor creditor,
                           @JsonProperty("executionDate") String executionDate,
                           @JsonProperty("uri") String uri,
                           @JsonProperty("description") String description,
                           @JsonProperty("amount") double amount,
                           @JsonProperty("currency") String currency,
                           @JsonProperty("isUrgent") boolean isUrgent,
                           @JsonProperty("isInstant") boolean isInstant,
                           @JsonProperty("feeType") String feeType,
                           @JsonProperty("feeAccountId") String feeAccountId,
                           @JsonProperty("taxRelief") TaxRelief taxRelief) {
        if (creditor == null || description == null || amount <= 0 || currency == null) {
            throw new IllegalArgumentException("Fields creditor, executionDate, description, amount, currency.");
        }
        this.creditor = creditor;
        this.executionDate = executionDate;
        this.uri = uri;
        this.description = description;
        this.amount = amount;
        this.currency = currency;
        this.isUrgent = isUrgent;
        this.isInstant = isInstant;
        this.feeType = feeType;
        this.feeAccountId = feeAccountId;
        this.taxRelief = taxRelief;
    }



    public TransferRequest() {
    }


    public Creditor getCreditor() {
        return creditor;
    }

    public void setCreditor(Creditor creditor) {
        this.creditor = creditor;
    }

    public String getExecutionDate() {
        return executionDate;
    }

    public void setExecutionDate(String executionDate) {
        this.executionDate = executionDate;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public boolean getIsUrgent() {
        return isUrgent;
    }

    public void setIsUrgent(boolean urgent) {
        isUrgent = urgent;
    }

    public boolean getIsInstant() {
        return isInstant;
    }

    public void setIsInstant(boolean instant) {
        isInstant = instant;
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public String getFeeAccountId() {
        return feeAccountId;
    }

    public void setFeeAccountId(String feeAccountId) {
        this.feeAccountId = feeAccountId;
    }


    public TaxRelief getTaxRelief() {
        return taxRelief;
    }

    public void setTaxRelief(TaxRelief taxRelief) {
        this.taxRelief = taxRelief;
    }
}
