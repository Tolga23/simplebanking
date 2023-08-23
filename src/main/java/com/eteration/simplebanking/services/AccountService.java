package com.eteration.simplebanking.services;


import com.eteration.simplebanking.converter.AccountConverter;
import com.eteration.simplebanking.dto.AccountDto;
import com.eteration.simplebanking.dto.AccountSaveRequestDto;
import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.services.entityservice.AccountEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

// This class is a place holder you can change the complete implementation
@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountEntityService accountEntityService;
    private final AccountConverter accountConverter;

    public AccountDto findAccount(String accountNumber) {
        Account account = accountEntityService.getAccountByAccountNumber(accountNumber);
        AccountDto accountDto = accountConverter.toDto(account);

        return accountDto;
    }

    public AccountDto createUser(AccountSaveRequestDto accountSaveRequestDto) {
        String accountNumber = getAccountNumber();

        Account account = accountConverter.convertToSaveRequestDtoToAccount(accountSaveRequestDto);
        account.setAccountNumber(accountNumber);

        account = accountEntityService.save(account);

        AccountDto accountDto = accountConverter.toDto(account);

        return accountDto;
    }

    private String getAccountNumber() {
        Random random = new Random();

        int firstThreeDigits = random.nextInt(900) + 100;

        int nextFourDigits = random.nextInt(9000) + 1000;

        return firstThreeDigits + "-" + nextFourDigits;
    }



}
