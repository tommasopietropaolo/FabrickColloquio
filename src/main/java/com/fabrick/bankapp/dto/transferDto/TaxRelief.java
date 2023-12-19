package com.fabrick.bankapp.dto.transferDto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class TaxRelief implements Serializable {
    private String taxReliefId;
    //required
    private boolean isCondoUpgrade;
    //required
    private String creditorFiscalCode;
    //required
    private String beneficiaryType;
    private NaturalPersonBeneficiary naturalPersonBeneficiary;
    private LegalPersonBeneficiary legalPersonBeneficiary;

    @JsonCreator
    public TaxRelief(@JsonProperty("taxReliefId") String taxReliefId,
                     @JsonProperty("isCondoUpgrade") boolean isCondoUpgrade,
                     @JsonProperty("creditorFiscalCode") String creditorFiscalCode,
                     @JsonProperty("beneficiaryType") String beneficiaryType,
                     @JsonProperty("naturalPersonBeneficiary") NaturalPersonBeneficiary naturalPersonBeneficiary,
                     @JsonProperty("legalPersonBeneficiary") LegalPersonBeneficiary legalPersonBeneficiary) {
        this.taxReliefId = taxReliefId;
        this.isCondoUpgrade = isCondoUpgrade;
        this.creditorFiscalCode = creditorFiscalCode;
        this.beneficiaryType = beneficiaryType;
        this.naturalPersonBeneficiary = naturalPersonBeneficiary;
        this.legalPersonBeneficiary = legalPersonBeneficiary;
    }

    public TaxRelief() {
    }

    public String getTaxReliefId() {
        return taxReliefId;
    }

    public void setTaxReliefId(String taxReliefId) {
        this.taxReliefId = taxReliefId;
    }

    public boolean getIsCondoUpgrade() {
        return isCondoUpgrade;
    }

    public void setIsCondoUpgrade(boolean condoUpgrade) {
        isCondoUpgrade = condoUpgrade;
    }

    public String getCreditorFiscalCode() {
        return creditorFiscalCode;
    }

    public void setCreditorFiscalCode(String creditorFiscalCode) {
        this.creditorFiscalCode = creditorFiscalCode;
    }

    public String getBeneficiaryType() {
        return beneficiaryType;
    }

    public void setBeneficiaryType(String beneficiaryType) {
        this.beneficiaryType = beneficiaryType;
    }

    public NaturalPersonBeneficiary getNaturalPersonBeneficiary() {
        return naturalPersonBeneficiary;
    }

    public void setNaturalPersonBeneficiary(NaturalPersonBeneficiary naturalPersonBeneficiary) {
        this.naturalPersonBeneficiary = naturalPersonBeneficiary;
    }

    public LegalPersonBeneficiary getLegalPersonBeneficiary() {
        return legalPersonBeneficiary;
    }

    public void setLegalPersonBeneficiary(LegalPersonBeneficiary legalPersonBeneficiary) {
        this.legalPersonBeneficiary = legalPersonBeneficiary;
    }


}
