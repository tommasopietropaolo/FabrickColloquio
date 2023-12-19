package com.fabrick.bankapp.dto.transferDto;

import com.fabrick.bankapp.dto.balanceDto.Balance;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
    @Test
    public void shouldCreateAccountCorrectly() {
        //given
        String accountCodeValue = "123";
        String bicCodeValue = "123";
        //when
        Account newAccount = new Account(accountCodeValue,bicCodeValue);
        //then
        assertThat(newAccount).isNotNull();
        assertThat(newAccount.getAccountCode()).isEqualTo(accountCodeValue);
        assertThat(newAccount.getBicCode()).isEqualTo(bicCodeValue);
    }

}