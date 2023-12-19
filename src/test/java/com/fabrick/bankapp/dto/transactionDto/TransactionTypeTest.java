package com.fabrick.bankapp.dto.transactionDto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class TransactionTypeTest {
    @Test
    public void shouldCreateTransactionTypeCorrectly(){
        //given
        String enumerationValue="dummy";
        String value="dummy";
        //when
        TransactionType transactionTypeValue=new TransactionType(enumerationValue,value);
        //then
        assertThat(transactionTypeValue).isNotNull();
        assertThat(transactionTypeValue.getValue()).isEqualTo(value);
        assertThat(transactionTypeValue.getEnumeration()).isEqualTo(enumerationValue);
    }

}