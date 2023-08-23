package com.eteration.simplebanking.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class TransactionDto {

    private Date date;
    private double amount;
    private String type;
    private String approvalCode;

}
