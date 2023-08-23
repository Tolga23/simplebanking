package com.eteration.simplebanking.dto;

import com.eteration.simplebanking.model.Transaction;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class AccountDto {

    private String accountNumber;
    private String owner;
    private double balance;
    private Date createDate;

    private List<TransactionDto> transactions;
}
