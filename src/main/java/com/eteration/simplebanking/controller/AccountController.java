package com.eteration.simplebanking.controller;

import com.eteration.simplebanking.base.dto.RestResponse;
import com.eteration.simplebanking.dto.AccountDto;
import com.eteration.simplebanking.dto.AccountSaveRequestDto;
import com.eteration.simplebanking.dto.TransactionDto;
import com.eteration.simplebanking.model.DepositTransaction;
import com.eteration.simplebanking.model.WithdrawalTransaction;
import com.eteration.simplebanking.services.AccountService;
import com.eteration.simplebanking.services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account/v1")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;
    private final TransactionService transactionService;

    @GetMapping("{accountNumber}")
    public ResponseEntity getAccount(@PathVariable String accountNumber) {
        AccountDto account = accountService.findAccount(accountNumber);

        return ResponseEntity.ok(RestResponse.of(account));
    }

    @PostMapping("/create")
    public ResponseEntity createUser(@RequestBody AccountSaveRequestDto accountSaveRequestDto) {
        AccountDto account = accountService.createUser(accountSaveRequestDto);

        return ResponseEntity.ok(RestResponse.of(account));
    }

    @PostMapping("/credit/{accountNumber}")
    public ResponseEntity<TransactionStatus> credit(@PathVariable String accountNumber, @RequestBody DepositTransaction depositTransaction) {
        transactionService.credit(accountNumber, depositTransaction);

        return ResponseEntity.ok(new TransactionStatus("Deposit Successful"));
    }

    @PostMapping("/debit/{accountNumber}")
    public ResponseEntity debit(@PathVariable String accountNumber, @RequestBody WithdrawalTransaction transaction) {
        transactionService.debit(accountNumber, transaction);

        return ResponseEntity.ok(new TransactionStatus("Withdraw Successful").toString());
    }

    @GetMapping("/test/{accountNumber}")
    public ResponseEntity findAllTransactionByAccountNumber(@PathVariable String accountNumber) {
        List<TransactionDto> transactionByAccountAccountNumber = transactionService.findTransactionByAccountAccountNumber(accountNumber);
        return ResponseEntity.ok(transactionByAccountAccountNumber);
    }
}