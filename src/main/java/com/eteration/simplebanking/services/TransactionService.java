package com.eteration.simplebanking.services;

import com.eteration.simplebanking.base.CustomErrorMessages;
import com.eteration.simplebanking.converter.TransactionConverter;
import com.eteration.simplebanking.dto.PhoneBillPaymentDto;
import com.eteration.simplebanking.dto.TransactionDto;
import com.eteration.simplebanking.exception.InsufficientBalanceException;
import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.PhoneBillPaymentTransaction;
import com.eteration.simplebanking.model.Transaction;
import com.eteration.simplebanking.services.entityservice.AccountEntityService;
import com.eteration.simplebanking.services.entityservice.TransactionEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TransactionService {
    private final AccountEntityService accountService;
    private final TransactionEntityService transactionEntityService;
    private final TransactionConverter transactionConverter;

    public void credit(String accountNumber, Transaction transaction) {
        double amount = transaction.getAmount();
        validateDepositAmount(amount);

        Account account = accountService.getAccountByAccountNumber(accountNumber);

        account.post(transaction);

        updateTransactionDetails(transaction, account);

        accountService.save(account);
    }

    public void debit(String accountNumber, Transaction transaction) {
        double amount = transaction.getAmount();
        Account account = accountService.getAccountByAccountNumber(accountNumber);
        double accountBalance = account.getBalance();

        validateAccountBalance(amount, accountBalance);

        account.post(transaction);

        updateTransactionDetails(transaction, account);

        accountService.save(account);
    }


    public void phoneBillPayment(String accountNumber, PhoneBillPaymentDto phoneBillPaymentDto) {
        PhoneBillPaymentTransaction phoneBillPaymentTransaction = transactionConverter.convertToPhoneBillPaymentTransaction(phoneBillPaymentDto);

        debit(accountNumber, phoneBillPaymentTransaction);
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

    public List<TransactionDto> findTransactionByAccountAccountNumber(String accountNumber) {
        List<Transaction> transactions = transactionEntityService.findTransactionByAccountAccountNumber(accountNumber);

        List<TransactionDto> dtoList = transactionConverter.toDtoList(transactions);

        return dtoList;
    }
}
