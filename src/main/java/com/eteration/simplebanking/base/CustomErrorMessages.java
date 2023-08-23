package com.eteration.simplebanking.base;

import com.eteration.simplebanking.base.exception.BaseErrorMessages;

public enum CustomErrorMessages implements BaseErrorMessages {
    INSUFFICIENT_BALANCE("Insufficient balance"),
    ACCOUNT_NOT_FOUND("Account not found"),
    INVALID_DEPOSIT_AMOUNT("Invalid deposit amount");

    String message;
    CustomErrorMessages(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return message;
    }
}
