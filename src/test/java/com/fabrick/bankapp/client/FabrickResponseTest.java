package com.fabrick.bankapp.client;

import com.fabrick.bankapp.dto.balanceDto.Balance;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FabrickResponseTest {
    @Test
    public void shouldCreateFabrickResponseCorrectly(){
        //given
         String statusValue="success";
         List<String> errorsValue= Arrays.asList("Error1", "Error2");
        String payloadValue="Payload";
        //when
        FabrickResponse newFabrickResponse = new FabrickResponse(statusValue,errorsValue,payloadValue);
        //then
        assertThat(newFabrickResponse).isNotNull();
        assertThat(newFabrickResponse.getStatus()).isEqualTo(statusValue);
        assertThat(newFabrickResponse.getErrors()).isEqualTo(errorsValue);
        assertThat(newFabrickResponse.getPayload()).isEqualTo(payloadValue);
    }


}