package com.fabrick.bankapp.dto.balanceDto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BalanceTest {
    @Test
    public void shouldCreateBalanceCorrectly(){
        //given
        Double balanceValue = 45.1;
        //when
        Balance newBalance = new Balance(balanceValue);
        //then
        assertThat(newBalance).isNotNull();
        assertThat(newBalance.getBalance()).isEqualTo(balanceValue);
    }

}