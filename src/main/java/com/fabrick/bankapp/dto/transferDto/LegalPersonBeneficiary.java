package com.fabrick.bankapp.dto.transferDto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class LegalPersonBeneficiary implements Serializable {
    //required
    private String fiscalCode;
    private String legalRepresentativeFiscalCode;

    @JsonCreator
    public LegalPersonBeneficiary(@JsonProperty("fiscalCode") String fiscalCode,
                                  @JsonProperty("legalRepresentativeFiscalCode") String legalRepresentativeFiscalCode) {
        this.fiscalCode = fiscalCode;
        this.legalRepresentativeFiscalCode = legalRepresentativeFiscalCode;
    }

    public String getFiscalCode() {
        return fiscalCode;
    }

    public String getLegalRepresentativeFiscalCode() {
        return legalRepresentativeFiscalCode;
    }

    public void setFiscalCode(String fiscalCode) {
        this.fiscalCode = fiscalCode;
    }

    public void setLegalRepresentativeFiscalCode(String legalRepresentativeFiscalCode) {
        this.legalRepresentativeFiscalCode = legalRepresentativeFiscalCode;
    }

    @Override
    public String toString() {
        return "LegalPersonBeneficiary{" +
                "fiscalCode='" + fiscalCode + '\'' +
                ", legalRepresentativeFiscalCode='" + legalRepresentativeFiscalCode + '\'' +
                '}';
    }
}
