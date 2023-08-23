package com.eteration.simplebanking.services.entityservice;

import com.eteration.simplebanking.base.service.BaseEntityService;
import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.repository.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountEntityService extends BaseEntityService<Account, AccountRepository> {
    public AccountEntityService(AccountRepository repo) {
        super(repo);
    }

    public Account getAccountByAccountNumber(String accountNumber) {
        return getRepo().getAccountByAccountNumber(accountNumber);
    }


}
