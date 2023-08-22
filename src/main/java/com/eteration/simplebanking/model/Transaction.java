package com.eteration.simplebanking.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "TRANSACTION")
public abstract class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATE")
    private Date date;

    @Column(name = "AMOUNT")
    private double amount;

    @ManyToOne
    @JoinColumn(name = "ACCOUNT_ID")
    private Account account;
}

