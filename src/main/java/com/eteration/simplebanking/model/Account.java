package com.eteration.simplebanking.model;


import com.eteration.simplebanking.base.CustomErrorMessages;
import com.eteration.simplebanking.base.model.BaseEntity;
import com.eteration.simplebanking.exception.InsufficientBalanceException;
import lombok.*;

import javax.management.InstanceNotFoundException;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "ACCOUNT")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Account extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "OWNER")
    private String owner;

    @Column(name = "ACCOUNT_NUMBER")
    private String accountNumber;

    @Column(name = "BALANCE")
    private double balance;

    @OneToMany(mappedBy = "account",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            targetEntity = Transaction.class)
    private List<Transaction> transactions = new ArrayList<>();

    public Account(String owner, String accountNumber) {
        this.owner = owner;
        this.accountNumber = accountNumber;
    }

    public void deposit(double amount) {
        if (amount < 0) {
            throw new InsufficientBalanceException(CustomErrorMessages.INSUFFICIENT_BALANCE);
        }

        balance += amount;
    }

    public void withdraw(double amount) {
        if (amount > balance || amount < 0)
            throw new InsufficientBalanceException(CustomErrorMessages.INSUFFICIENT_BALANCE);

        balance -= amount;
    }

    public void post(Transaction transaction) {
        if (transaction instanceof DepositTransaction) {
            DepositTransaction depositTransaction = (DepositTransaction) transaction;
            deposit(depositTransaction.getAmount());
            transactions.add(depositTransaction);
        } else if (transaction instanceof WithdrawalTransaction) {
            WithdrawalTransaction withdrawalTransaction = (WithdrawalTransaction) transaction;
            withdraw(withdrawalTransaction.getAmount());
            transactions.add(withdrawalTransaction);
        }
    }

}
