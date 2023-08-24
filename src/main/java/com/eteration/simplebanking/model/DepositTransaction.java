package com.eteration.simplebanking.model;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("DepositTransaction")
public class DepositTransaction extends Transaction {

    public DepositTransaction(double amount) {
        super(amount);
    }

    public DepositTransaction() {

    }

    @Override
    public void execute(Account account) {
        double amount = getAmount();
        account.deposit(amount);
    }
}
