package com.eteration.simplebanking.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("WithdrawTransaction")
public class WithdrawalTransaction extends Transaction {

    public WithdrawalTransaction(double amount) {
        super(amount);
    }

    public WithdrawalTransaction() {

    }

    @Override
    public void execute(Account account) {
        double amount = getAmount();
        double currentBalance = account.withdraw(amount);
        account.setBalance(currentBalance);
    }
}


