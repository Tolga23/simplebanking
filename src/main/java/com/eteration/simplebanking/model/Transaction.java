package com.eteration.simplebanking.model;

import com.eteration.simplebanking.base.model.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "TRANSACTION")
@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "transaction_type")
public abstract class Transaction extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATE")
    private Date date;

    @Column(name = "AMOUNT")
    private double amount;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "ACCOUNT_ID", nullable = false, foreignKey = @ForeignKey(name = "FK_TRANSACTION_ACCOUNT"))
    private Account account;

    public Transaction(double amount) {
        this.date = new Date();
        this.amount = amount;
    }

    public Transaction() {
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", date=" + date +
                ", amount=" + amount +
                ", account=" + account +
                '}';
    }

    public abstract void execute(Account account);
}

