package com.fabrick.bankapp.controller;

import com.fabrick.bankapp.dto.balanceDto.Balance;
import com.fabrick.bankapp.dto.transactionDto.Transaction;
import com.fabrick.bankapp.service.FabrickApiService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("")
public class AccountController {

    private final FabrickApiService fabrickApiService;

    public AccountController(FabrickApiService fabrickApiService) {
        this.fabrickApiService = fabrickApiService;
    }

    @GetMapping("/saldo")
    public Balance getBalance() {
        return fabrickApiService.getBalance();
    }

    @GetMapping("/transactionList")
    public List<Transaction> getTransactions(
            @RequestParam String fromAccountingDate,
            @RequestParam String toAccountingDate) {
        return fabrickApiService.getTransactions(fromAccountingDate, toAccountingDate);

    }


}