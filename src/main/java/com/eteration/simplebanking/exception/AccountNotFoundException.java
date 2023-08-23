package com.eteration.simplebanking.exception;

import com.eteration.simplebanking.base.exception.BaseErrorMessages;
import com.eteration.simplebanking.base.exception.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AccountNotFoundException extends BaseException {
    public AccountNotFoundException(BaseErrorMessages baseErrorMessages) {
        super(baseErrorMessages);
    }
}
