package com.fabrick.bankapp.dto.transferDto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CreditorTest {
    @Test
    public void shouldCreateCreditorCorrectly() {
        //given
        String nameValue="roberto";
        Account accountValue=new Account("123","456");
        Address addressValue=new Address("dummy","756","dummycity");
        //when
        Creditor newCreditor = new Creditor(nameValue,accountValue,addressValue);
        //then
        assertThat(newCreditor).isNotNull();
        assertThat(newCreditor.getName()).isEqualTo(nameValue);
        assertThat(newCreditor.getAccount()).isEqualTo(accountValue);
        assertThat(newCreditor.getAddress()).isEqualTo(addressValue);
    }

}