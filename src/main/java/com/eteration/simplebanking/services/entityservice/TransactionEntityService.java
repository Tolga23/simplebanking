package com.eteration.simplebanking.services.entityservice;

import com.eteration.simplebanking.base.service.BaseEntityService;
import com.eteration.simplebanking.model.Transaction;
import com.eteration.simplebanking.repository.TransactionRepository;
import org.springframework.stereotype.Service;

@Service
public class TransactionEntityService extends BaseEntityService<Transaction, TransactionRepository> {
    public TransactionEntityService(TransactionRepository repo) {
        super(repo);
    }
}
