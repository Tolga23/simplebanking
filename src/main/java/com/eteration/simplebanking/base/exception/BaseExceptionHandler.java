package com.eteration.simplebanking.base.exception;

import com.eteration.simplebanking.base.dto.RestResponse;
import com.eteration.simplebanking.exception.AccountNotFoundException;
import com.eteration.simplebanking.exception.InsufficientBalanceException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@ControllerAdvice
@RequiredArgsConstructor
public class BaseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<Object> handleInsufficientBalanceException(InsufficientBalanceException ex) {
        String errorMessage = "An error occurred: " + ex.getBaseErrorMessages().getMessage();

        RestResponse<String> restResponse = RestResponse.error(errorMessage);
        restResponse.setMessage(errorMessage);


        return new ResponseEntity<>(restResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<Object> handleAccountNotFoundException(AccountNotFoundException ex) {
        String errorMessage = "An error occurred: " + ex.getBaseErrorMessages().getMessage();

        RestResponse<String> restResponse = RestResponse.error(errorMessage);
        restResponse.setMessage(errorMessage);

        return new ResponseEntity<>(restResponse, HttpStatus.NOT_FOUND);
    }
}
