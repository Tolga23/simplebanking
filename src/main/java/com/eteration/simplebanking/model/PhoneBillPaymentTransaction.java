package com.eteration.simplebanking.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("PhoneBillPaymentTransaction")
@Getter
@Setter
public class PhoneBillPaymentTransaction extends Transaction {
    private String payee;
    private String phoneNumber;

    public PhoneBillPaymentTransaction() {

    }

    public PhoneBillPaymentTransaction(String payee, double amount, String phoneNumber) {
        super(amount);
        this.payee = payee;
        this.phoneNumber = phoneNumber;
    }
}
