package com.fabrick.bankapp.dto.transferDto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class TaxReliefTest {
    @Test
    public void shouldCreateTaxReliefCorrectly() {
        //given
        String taxReliefId = "123";
        boolean isCondoUpgrade = true;
        String creditorFiscalCode = "ABC123";
        String beneficiaryType = "NaturalPerson";
        NaturalPersonBeneficiary naturalPersonBeneficiary = new NaturalPersonBeneficiary("fiscalCode1",
                "fiscalCode2",
                "fiscalCode3",
                "fiscalCode4",
                "fiscalCode5");
        LegalPersonBeneficiary legalPersonBeneficiary = new LegalPersonBeneficiary("fiscalCodeValue",
                "legalRepresentativeFiscalCodeValue");
        //when
        TaxRelief newTaxRelief = new TaxRelief(taxReliefId,
                isCondoUpgrade,
                creditorFiscalCode,
                beneficiaryType,
                naturalPersonBeneficiary,
                legalPersonBeneficiary);
        //then
        assertThat(newTaxRelief).isNotNull();
        assertThat(newTaxRelief.getTaxReliefId()).isEqualTo(taxReliefId);
        assertThat(newTaxRelief.getIsCondoUpgrade()).isEqualTo(isCondoUpgrade);
        assertThat(newTaxRelief.getCreditorFiscalCode()).isEqualTo(creditorFiscalCode);
        assertThat(newTaxRelief.getBeneficiaryType()).isEqualTo(beneficiaryType);
        assertThat(newTaxRelief.getNaturalPersonBeneficiary()).isEqualTo(naturalPersonBeneficiary);
        assertThat(newTaxRelief.getLegalPersonBeneficiary()).isEqualTo(legalPersonBeneficiary);
    }

}