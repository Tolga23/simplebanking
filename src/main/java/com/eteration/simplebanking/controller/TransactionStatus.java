package com.eteration.simplebanking.controller;


// This class is a place holder you can change the complete implementation

import lombok.Getter;
import lombok.Setter;

import java.util.Random;
import java.util.UUID;

@Getter
@Setter
public class TransactionStatus {
    private String status;
    private String approvalCode;

    public TransactionStatus(String status){
        this.status = status;
        this.approvalCode = getApprovalCode();
    }
    private String getApprovalCode(){
        String randomUuid = UUID.randomUUID().toString();
        String code = randomUuid.replace("-", "");
        StringBuilder formattedCode = new StringBuilder(code);

        formattedCode.insert(8, "-");
        formattedCode.insert(13, "-");
        formattedCode.insert(18, "-");
        formattedCode.insert(23, "-");

        return formattedCode.toString();
    }
}
