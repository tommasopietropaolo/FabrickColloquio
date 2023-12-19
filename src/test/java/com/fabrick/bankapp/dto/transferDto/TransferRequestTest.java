package com.fabrick.bankapp.dto.transferDto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class TransferRequestTest {
    @Test
    public void shouldCreateTransferRequestCorrectly() {
        //given
        Account accountDummy=new Account("123","456");
        Address addressDummy=new Address("dummy","756","dummycity");
        NaturalPersonBeneficiary naturalPersonBeneficiaryDummy = new NaturalPersonBeneficiary("fiscalCode1",
                "fiscalCode2",
                "fiscalCode3",
                "fiscalCode4",
                "fiscalCode5");
        LegalPersonBeneficiary legalPersonBeneficiaryDummy = new LegalPersonBeneficiary("fiscalCodeValue",
                "legalRepresentativeFiscalCodeValue");

        Creditor creditor=new Creditor("dummy name",accountDummy,addressDummy);
        String executionDate="2000-01-01";
        String uri="dummyUri";
        String description="dummyDescription";
        double amount=80.0;
        String currency="EUR";
        boolean isUrgent=false;
        boolean isInstant=false;
        String feeType="dummyType";
        String feeAccountId="123";
        TaxRelief taxRelief=new TaxRelief("123",
                true,
                "ABC123",
                "NaturalPerson",
                naturalPersonBeneficiaryDummy,
                legalPersonBeneficiaryDummy);
        //when
        TransferRequest newTransferRequest = new TransferRequest(creditor,
                executionDate,
                uri,
                description,
                amount,
                currency,
                isUrgent,
                isInstant,
                feeType,
                feeAccountId,
                taxRelief);
        //then
        assertThat(newTransferRequest).isNotNull();
        assertThat(newTransferRequest.getCreditor()).isEqualTo(creditor);
        assertThat(newTransferRequest.getExecutionDate()).isEqualTo(executionDate);
        assertThat(newTransferRequest.getUri()).isEqualTo(uri);
        assertThat(newTransferRequest.getDescription()).isEqualTo(description);
        assertThat(newTransferRequest.getAmount()).isEqualTo(amount);
        assertThat(newTransferRequest.getCurrency()).isEqualTo(currency);
        assertThat(newTransferRequest.getIsUrgent()).isEqualTo(isUrgent);
        assertThat(newTransferRequest.getIsInstant()).isEqualTo(isInstant);
        assertThat(newTransferRequest.getFeeType()).isEqualTo(feeType);
        assertThat(newTransferRequest.getFeeAccountId()).isEqualTo(feeAccountId);
        assertThat(newTransferRequest.getTaxRelief()).isEqualTo(taxRelief);
    }

}