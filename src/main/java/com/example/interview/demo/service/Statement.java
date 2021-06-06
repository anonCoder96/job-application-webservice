package com.example.interview.demo.service;

import com.example.interview.demo.model.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * An account statement for an account given ID id, with current balance, ingoing and outgoing transactions.
 */
public class Statement {

    /**
     * The users balance at time of statement generation.
     */
    private double balance;

    /**
     * The id of the account this is a statement for.
     */
    private long id;

    /**
     * The list of ingoing transactions sorted by date/time.
     */
    private final List<Transaction> ingoingTransactions = new ArrayList<>();

    /**
     * The list of outgoing transactions sorted by date/time.
     */
    private final List<Transaction> outGoingTransactions = new ArrayList<>();

    /**
     * Constructor
     *
     * @param balance the accounts current balance.
     * @param id the accounts ID number.
     */
    public Statement(final double balance, final long id) {
        this.balance = balance;
        this.id = id;
    }

    /**
     * Adds a list of ingoing transactions to the current list of ingoing transactions.
     *
     * @param ingoingTransactions new ingoing transactions to add.
     */
    public void addIngoingTransactions(List<Transaction> ingoingTransactions) {
        this.ingoingTransactions.addAll(ingoingTransactions);
    }

    /**
     * Adds a list of outgoing transactions to the current list of outgoing transactions.
     *
     * @param outGoingTransactions new outgoing transactions to add.
     */
    public void addOutgoingTransactions(List<Transaction> outGoingTransactions) {
        this.outGoingTransactions.addAll(outGoingTransactions);
    }

    public double getBalance() {
        return balance;
    }

    public long getId() {
        return id;
    }
}
