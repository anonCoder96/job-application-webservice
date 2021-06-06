package com.example.interview.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.Instant;
import java.util.Date;

/**
 * A single transaction between two accounts.
 */
@Entity
@Table(name = "transaction")
public class Transaction extends Identifiable {

    /**
     * The ID of the account receiving money as a result of this transaction.
     */
    @Column(name = "recipient_id", nullable = false)
    private long recipientId;

    /**
     * The ID of the account sending money as a part of this transaction.
     */
    @Column(name = "payee_id", nullable = false)
    private long payeeId;

    /**
     * The Quantity of money to be transferred.
     */
    @Column(name = "payement", nullable = false)
    private double payment;

    /**
     * The date at which the transaction was performed.
     */
    @Column(name = "date", nullable = false)
    private Date date;

    /**
     * A flag to mark whether a transaction has been successfully completed.
     */
    @Column(name = "success")
    private boolean success;

    /**
     * A flag to mark if a transaction has been executed (or attempted to in the event it failed).
     */
    @Column(name = "done")
    private boolean done;

    /**
     * Marks a transaction as complete and sets a flag describing if it was successful or not.
     *
     * @param success set to true if the transaction was a success.
     * @throws Exception if the transaction was already completed.
     */
    public void complete(final boolean success) throws Exception {
        if (!done) {
            this.success = success;
            this.done = true;
        } else {
            throw new Exception("Transaction is already completed");
        }
    }

    /**
     * Constructor.
     *
     * @param payeeId the ID of the payee's account.
     * @param recipientId the ID of the recipients account.
     * @param payment the payment amount.
     */
    public Transaction(final long payeeId, final long recipientId, final double payment) {
        this();
        this.payeeId = payeeId;
        this.recipientId = recipientId;
        this.payment = payment;
    }

    private Transaction() {
        date = Date.from(Instant.now());
        success = false;
        done = false;
    }

    public long getRecipientId() {
        return recipientId;
    }

    public long getPayeeId() {
        return payeeId;
    }

    public double getPayment() {
        return payment;
    }

    public boolean isSuccess() {
        return success;
    }

    public boolean isDone() {
        return done;
    }

    public Date getDate() {
        return date;
    }
}
