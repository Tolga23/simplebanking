package com.eteration.simplebanking.controller;


// This class is a place holder you can change the complete implementation

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Random;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Component
public class TransactionStatus {
    private String status;
    private String approvalCode;

    public TransactionStatus(String status){
        getApprovalCode();
        this.status = status;
    }


    public String getApprovalCode(){
        String randomUuid = UUID.randomUUID().toString();
        String code = randomUuid.replace("-", "");
        StringBuilder formattedCode = new StringBuilder(code);

        formattedCode.insert(8, "-");
        formattedCode.insert(13, "-");
        formattedCode.insert(18, "-");
        formattedCode.insert(23, "-");

        this.approvalCode = formattedCode.toString();

        return formattedCode.toString();
    }

    @Override
    public String toString() {
        return "{\n" +
                "    \"status\": \"" + status + "\",\n" +
                "    \"approvalCode\": \"" + approvalCode + "\"\n" +
                "}";
    }
}
