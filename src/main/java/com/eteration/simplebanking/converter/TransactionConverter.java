package com.eteration.simplebanking.converter;

import com.eteration.simplebanking.base.converter.BaseConverter;
import com.eteration.simplebanking.controller.TransactionStatus;
import com.eteration.simplebanking.dto.TransactionDto;
import com.eteration.simplebanking.model.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TransactionConverter implements BaseConverter<Transaction, TransactionDto> {

    private final TransactionStatus transactionStatus;

    @Override
    public TransactionDto toDto(Transaction transaction) {
        TransactionDto transactionDto = TransactionDto.builder()
                .date(transaction.getDate())
                .amount(transaction.getAmount())
                .type(transaction.getClass().getSimpleName())
                .approvalCode(transactionStatus.getApprovalCode())
                .build();
        return transactionDto;
    }

    @Override
    public Transaction toEntities(TransactionDto transactionDto) {
        return null;
    }

    @Override
    public List<Transaction> toEntityList(List<TransactionDto> d) {
        return null;
    }

    @Override
    public List<TransactionDto> toDtoList(List<Transaction> transactionList) {
        List<TransactionDto> transactionDtoList = transactionList.stream().map(transaction -> toDto(transaction)).collect(Collectors.toList());

        return transactionDtoList;
    }
}
