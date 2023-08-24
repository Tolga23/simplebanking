package com.eteration.simplebanking.model;


import com.eteration.simplebanking.base.model.BaseEntity;
import lombok.*;

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

    public double deposit(double amount) {
        // amount control is done in the service layer
        balance += amount;

        return balance;
    }

    public double withdraw(double amount) {
        // amount control is done in the service layer
        balance -= amount;

        return balance;
    }

    public void post(Transaction transaction) {
        transaction.execute(this);
    }

}
