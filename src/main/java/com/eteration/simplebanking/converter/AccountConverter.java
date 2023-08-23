package com.eteration.simplebanking.converter;

import com.eteration.simplebanking.base.converter.BaseConverter;
import com.eteration.simplebanking.dto.AccountDto;
import com.eteration.simplebanking.dto.AccountSaveRequestDto;
import com.eteration.simplebanking.model.Account;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AccountConverter implements BaseConverter<Account, AccountDto> {
    @Override
    public AccountDto toDto(Account account) {
        AccountDto accountDto = AccountDto.builder()
                .accountNumber(account.getAccountNumber())
                .owner(account.getOwner())
                .balance(account.getBalance())
                .createDate(new Date())
                .build();

        return accountDto;
    }

    @Override
    public Account toEntities(AccountDto accountDto) {
        Account account = new Account();
        account.setAccountNumber(accountDto.getAccountNumber());
        account.setOwner(accountDto.getOwner());
        account.setBalance(accountDto.getBalance());

        return account;
    }

    @Override
    public List<Account> toEntityList(List<AccountDto> accountDtoList) {
        List<Account> accountList = accountDtoList.stream().map(accountDto -> toEntities(accountDto)).collect(Collectors.toList());

        return accountList;
    }

    @Override
    public List<AccountDto> toDtoList(List<Account> accountList) {
        List<AccountDto> accountDtoList = accountList.stream().map(account -> toDto(account)).collect(Collectors.toList());

        return accountDtoList;
    }

    public Account convertToSaveRequestDtoToAccount(AccountSaveRequestDto accountSaveRequestDto){
        Account account = new Account();
        account.setOwner(accountSaveRequestDto.getOwner());
        account.setBalance(accountSaveRequestDto.getBalance());

        return account;
    }
}
