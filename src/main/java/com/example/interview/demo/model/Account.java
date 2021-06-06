package com.example.interview.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * An account at this bank, stores the owners money for transactions.
 */
@Entity
@Table(name = "account")
public class Account extends Identifiable {

    /**
     * The accounts balance (how much money the owner has).
     */
    @Column(name = "balance", nullable = false)
    private double balance;

    /**
     * Constructor.
     *
     * @param balance the initial balance of the account.
     */
    public Account(double balance)
    {
        this.balance = balance;
    }

    /**
     * Constructor.
     */
    public Account() {}

    /**
     * Check if the account is able to afford a transaction.
     *
     * @param amount the size of the potential paid amount.
     * @return true if the account can afford the transaction.
     */
    public boolean canPay(final double amount) {
        return amount < balance;
    }

    /**
     * Deducts some money from the account.
     *
     * @param amount the amount of money.
     */
    public void deduct(final double amount) {
        balance -= amount;
    }

    /**
     * Pays some money in to the account.
     *
     * @param amount the amount of money.
     */
    public void payIn(final double amount) {
        balance += amount;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
