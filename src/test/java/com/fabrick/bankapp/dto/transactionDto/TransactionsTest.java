package com.fabrick.bankapp.dto.transactionDto;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TransactionsTest {


    @Test
    public void shouldCreateTransactionsCorrectly() {
        //given
        List<Transaction> transactions = new ArrayList<>();
        Transaction transaction1 = new Transaction("1", "OP1", "2023-01-01", "2023-01-01",new TransactionType("dummy", "123"), 100.0, "EUR", "Deposit transaction");
        Transaction transaction2 = new Transaction("2", "OP2", "2023-01-02", "2023-01-02", new TransactionType("dummy", "123"), 50.0, "USD", "Withdrawal transaction");
        Transaction transaction3 = new Transaction("3", "OP3", "2023-01-03", "2023-01-03", new TransactionType("dummy", "123"), 75.0, "GBP", "Purchase transaction");
        //when
        transactions.add(transaction1);
        transactions.add(transaction2);
        transactions.add(transaction3);
        //then
        assertEquals(3, transactions.size());
        assertEquals("1", transactions.get(0).getTransactionId());
        assertEquals("OP1", transactions.get(0).getOperationId());
        assertEquals("2023-01-01", transactions.get(0).getAccountingDate());
        assertEquals("2023-01-01", transactions.get(0).getValueDate());
        assertEquals("dummy", transactions.get(0).getType().getEnumeration());
        assertEquals("123", transactions.get(0).getType().getValue());
        assertEquals(100.0, transactions.get(0).getAmount());
        assertEquals("EUR", transactions.get(0).getCurrency());
        assertEquals("Deposit transaction", transactions.get(0).getDescription());

    }

}