package com.fabrick.bankapp.dto.transferDto;

import com.fabrick.bankapp.dto.balanceDto.Balance;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class TransferTest {
    @Test
    public void shouldCreateTransferCorrectly(){
        //given
        String idValue = "123abc";
        //when
        Transfer newTransfer = new Transfer(idValue);
        //then
        assertThat(newTransfer).isNotNull();
        assertThat(newTransfer.getMoneyTransferId()).isEqualTo(idValue);
    }

}