package com.eteration.simplebanking.dto;

import lombok.Data;

@Data
public class AccountSaveRequestDto {
    private String owner;
    private double balance;
}
