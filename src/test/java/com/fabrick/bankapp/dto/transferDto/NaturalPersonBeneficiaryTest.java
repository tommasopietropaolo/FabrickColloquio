package com.fabrick.bankapp.dto.transferDto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class NaturalPersonBeneficiaryTest {

    @Test
    public void shouldCreateNaturalPersonBeneficiaryCorrectly() {
        //given
        String fiscalCode1 = "dummy1";
        String fiscalCode2 = "dummy2";
        String fiscalCode3 = "dummy3";
        String fiscalCode4 = "dummy4";
        String fiscalCode5 = "dummy5";
        //when
        NaturalPersonBeneficiary newNaturalPersonBeneficiary = new NaturalPersonBeneficiary(
                fiscalCode1,
                fiscalCode2,
                fiscalCode3,
                fiscalCode4,
                fiscalCode5);
        //then
        assertThat(newNaturalPersonBeneficiary).isNotNull();
        assertThat(newNaturalPersonBeneficiary.getFiscalCode1()).isEqualTo(fiscalCode1);
        assertThat(newNaturalPersonBeneficiary.getFiscalCode2()).isEqualTo(fiscalCode2);
        assertThat(newNaturalPersonBeneficiary.getFiscalCode3()).isEqualTo(fiscalCode3);
        assertThat(newNaturalPersonBeneficiary.getFiscalCode4()).isEqualTo(fiscalCode4);
        assertThat(newNaturalPersonBeneficiary.getFiscalCode5()).isEqualTo(fiscalCode5);
    }

}