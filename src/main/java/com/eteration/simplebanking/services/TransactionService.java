package com.eteration.simplebanking.services;

import com.eteration.simplebanking.base.CustomErrorMessages;
import com.eteration.simplebanking.converter.TransactionConverter;
import com.eteration.simplebanking.dto.PhoneBillPaymentDto;
import com.eteration.simplebanking.dto.TransactionDto;
import com.eteration.simplebanking.exception.InsufficientBalanceException;
import com.eteration.simplebanking.model.*;
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

    public void credit(String accountNumber, Transaction transaction) {
        double amount = transaction.getAmount();
        validateDepositAmount(amount);

        Account account = accountService.getAccountByAccountNumber(accountNumber);
        double accountBalance = account.getBalance();

        double currentBalance = depositOperation(accountBalance, amount);

        updateAccountDetails(transaction, account, currentBalance);

        updateTransactionDetails(transaction, account);

        accountService.save(account);
    }

    public void debit(String accountNumber, Transaction transaction) {
        double amount = transaction.getAmount();
        Account account = accountService.getAccountByAccountNumber(accountNumber);
        double accountBalance = account.getBalance();

        validateAccountBalance(amount, accountBalance);

        double currentBalance = withdrawOperation(accountBalance, amount);

        updateAccountDetails(transaction, account, currentBalance);

        updateTransactionDetails(transaction, account);

        accountService.save(account);
    }


    public void phoneBillPayment(String accountNumber, PhoneBillPaymentDto phoneBillPaymentDto) {
        PhoneBillPaymentTransaction phoneBillPaymentTransaction = transactionConverter.convertToPhoneBillPaymentTransaction(phoneBillPaymentDto);

        debit(accountNumber, phoneBillPaymentTransaction);
    }

    private void updateAccountDetails(Transaction transaction, Account account, double currentBalance) {
        account.getTransactions().add(transaction);
        account.setBalance(currentBalance);
    }

    private double withdrawOperation(double accountBalance, double amount) {
        double currentBalance = accountBalance - amount;
        return currentBalance;
    }

    private void validateDepositAmount(double amount) {
        if (amount <= 0) {
            throw new InsufficientBalanceException(CustomErrorMessages.INVALID_DEPOSIT_AMOUNT);
        }
    }

    private void validateAccountBalance(double amount, double accountBalance) {
        if (amount > accountBalance) {
            throw new InsufficientBalanceException(CustomErrorMessages.INSUFFICIENT_BALANCE);
        }
    }

    private void updateTransactionDetails(Transaction transaction, Account account) {
        transaction.setAccount(account);
        transaction.setDate(new Date());
    }

    private double depositOperation(double accountBalance, double amount) {
        return accountBalance + amount;
    }

    public List<TransactionDto> findTransactionByAccountAccountNumber(String accountNumber) {
        List<Transaction> transactions = transactionEntityService.findTransactionByAccountAccountNumber(accountNumber);

        List<TransactionDto> dtoList = transactionConverter.toDtoList(transactions);

        return dtoList;
    }
}
