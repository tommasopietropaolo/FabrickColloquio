package com.fabrick.bankapp.dto.transactionDto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TransactionTest {

    @Test
    public void shouldCreateTransactionCorrectly() {
        //given
        String transactionIdValue = "123";
        String operationIdValue = "123";
        String accountingDateValue = "dummy";
        String valueDateValue = "2000-01-01";
        TransactionType typeValue = new TransactionType("dummy", "123");
        double amountValue = 100.00;
        String currencyValue = "EUR";
        String descriptionValue = "dummy_description";

        //when
        Transaction newTransaction = new Transaction(
                transactionIdValue,
                operationIdValue,
                accountingDateValue,
                valueDateValue,
                typeValue,
                amountValue,
                currencyValue,
                descriptionValue);
        //then
        assertThat(newTransaction).isNotNull();
        assertThat(newTransaction.getTransactionId()).isEqualTo(transactionIdValue);
        assertThat(newTransaction.getOperationId()).isEqualTo(operationIdValue);
        assertThat(newTransaction.getAccountingDate()).isEqualTo(accountingDateValue);
        assertThat(newTransaction.getValueDate()).isEqualTo(valueDateValue);
        assertThat(newTransaction.getType()).isEqualTo(typeValue);
        assertThat(newTransaction.getAmount()).isEqualTo(amountValue);
        assertThat(newTransaction.getCurrency()).isEqualTo(currencyValue);
        assertThat(newTransaction.getDescription()).isEqualTo(descriptionValue);

    }

}