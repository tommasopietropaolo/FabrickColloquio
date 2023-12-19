package com.fabrick.bankapp.dto.transferDto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LegalPersonBeneficiaryTest {
    @Test
    public void shouldCreateLegalPersonBeneficiaryCorrectly() {
        //given
        String fiscalCodeValue="Dummy fiscal code";
        String legalRepresentativeFiscalCodeValue="Dummy legal code";
        //when
        LegalPersonBeneficiary newLegalPersonBeneficiary = new LegalPersonBeneficiary(fiscalCodeValue,legalRepresentativeFiscalCodeValue);
        //then
        assertThat(newLegalPersonBeneficiary).isNotNull();
        assertThat(newLegalPersonBeneficiary.getFiscalCode()).isEqualTo(fiscalCodeValue);
        assertThat(newLegalPersonBeneficiary.getLegalRepresentativeFiscalCode()).isEqualTo(legalRepresentativeFiscalCodeValue);
    }

}