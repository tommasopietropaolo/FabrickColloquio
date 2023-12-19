package com.fabrick.bankapp.controller;

import com.fabrick.bankapp.dto.balanceDto.Balance;
import com.fabrick.bankapp.dto.transactionDto.Transaction;
import com.fabrick.bankapp.dto.transactionDto.TransactionType;
import com.fabrick.bankapp.service.FabrickApiService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AccountController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    AccountController victim;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private FabrickApiService mockFabrickApiService;

    @Test
    void getBalanceShouldReturnT200AndValueFromService() throws Exception {
        Balance expectedBalance = new Balance(90.3);
        String expectedValue = new ObjectMapper().writeValueAsString(expectedBalance);
        when(mockFabrickApiService.getBalance()).thenReturn(expectedBalance);
        // Verifica che la risposta abbia lo status code atteso (200 OK nel caso di successo)
        ResultActions resultActions = this.mockMvc.perform(get("https://localhost:8080/saldo"))
                .andExpect(status().isOk());
        MvcResult result = resultActions.andReturn();
        String responseBody = result.getResponse().getContentAsString();
        assertThat(responseBody).isEqualTo(expectedValue);
    }

    @Test
    void getBalanceShouldReturnExceptionCodeFromService() throws Exception {
        when(mockFabrickApiService.getBalance()).thenThrow(new RuntimeException("dummy exception"));

        this.mockMvc
                .perform(get("https://localhost:8080/saldo"))
                .andExpect(status().is5xxServerError());
        verify(mockFabrickApiService).getBalance();

    }

    @Test
    void getTransactionsShouldReturn200AndValueFromService() throws Exception {
        String fromAccountingDateValue = "2011-01-01";
        String toAccountingDateValue = "2011-12-01";
        List<Transaction> expectedTtransactionList = new ArrayList<>();
        Transaction transaction1 = new Transaction("1", "OP1", "2023-01-01", "2023-01-01",
                new TransactionType("dummy", "123"), 100.0, "EUR", "Deposit transaction");
        expectedTtransactionList.add(transaction1);
        when(mockFabrickApiService.getTransactions(fromAccountingDateValue, toAccountingDateValue)).thenReturn(expectedTtransactionList);

        String expectedValue = new ObjectMapper().writeValueAsString(expectedTtransactionList);

        ResultActions resultActions = this.mockMvc.perform(get("https://localhost:8080/transactionList")
                .param("fromAccountingDate", fromAccountingDateValue)
                .param("toAccountingDate", toAccountingDateValue));

        resultActions.andExpect(status().isOk());
        MvcResult result = resultActions.andReturn();
        String responseBody = result.getResponse().getContentAsString();
        assertThat(responseBody).isEqualTo(expectedValue);
    }

    @Test
    void getTransactionsShouldReturnIllegalArgExceptionIfServiceIsThrowingTheException() throws Exception {

        when(mockFabrickApiService.getTransactions("2011-01-01", "2011-12-01")).thenThrow(new IllegalArgumentException("dummy exception"));

        this.mockMvc.perform(get("https://localhost:8080/transactionList")
                .param("fromAccountingDate", "2011-01-01")
                .param("toAccountingDate", "2011-12-01"));
        this.mockMvc
                .perform(get("https://localhost:8080/transactionList"))
                .andExpect(status().is4xxClientError());

    }
    
    @Test
    void getTransactionsShouldReturnExceptionCodeFromService() throws Exception {

        when(mockFabrickApiService.getTransactions("2011-01-01", "2011-12-01")).thenThrow(new RuntimeException("dummy exception"));

        this.mockMvc.perform(get("https://localhost:8080/transactionList")
                        .param("fromAccountingDate", "2011-01-01")
                        .param("toAccountingDate", "2011-12-01"))
                .andExpect(status().is5xxServerError());
        verify(mockFabrickApiService).getTransactions("2011-01-01", "2011-12-01");
    }


}