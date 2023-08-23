package com.eteration.simplebanking.services;

import com.eteration.simplebanking.converter.TransactionConverter;
import com.eteration.simplebanking.dto.TransactionDto;
import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.DepositTransaction;
import com.eteration.simplebanking.model.Transaction;
import com.eteration.simplebanking.model.WithdrawalTransaction;
import com.eteration.simplebanking.services.entityservice.AccountEntityService;
import com.eteration.simplebanking.services.entityservice.TransactionEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final AccountEntityService accountService;
    private final TransactionEntityService transactionEntityService;
    private final TransactionConverter transactionConverter;

    public void credit(String accountNumber, DepositTransaction transaction) {
        double amount = transaction.getAmount();

        Account account = accountService.getAccountByAccountNumber(accountNumber);
        // deposit logic
        double deposit = account.getBalance() + amount;
        account.getTransactions().add(transaction);
        account.setBalance(deposit);

        transaction.setAccount(account);
        transaction.setDate(new Date());

        accountService.save(account);
    }


    public void debit(String accountNumber, WithdrawalTransaction transaction) {
        double amount = transaction.getAmount();

        Account account = accountService.getAccountByAccountNumber(accountNumber);

        double accountBalance = account.getBalance();
        double currentBalance = accountBalance - amount;

        account.getTransactions().add(transaction);
        account.setBalance(currentBalance);

        transaction.setAccount(account);
        transaction.setDate(new Date());

        accountService.save(account);
    }

    public List<TransactionDto> findTransactionByAccountAccountNumber(String accountNumber) {
        List<Transaction> transactions = transactionEntityService.findTransactionByAccountAccountNumber(accountNumber);

        List<TransactionDto> dtoList = transactionConverter.toDtoList(transactions);

        return dtoList;
    }
}
