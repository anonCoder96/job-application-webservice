package com.example.interview.demo.service;

import com.example.interview.demo.model.Account;
import com.example.interview.demo.model.Transaction;
import javassist.tools.rmi.ObjectNotFoundException;
import org.jboss.logging.Logger;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * Service for executing actions on accounts.
 */
@Service
public class AccountService {

    private final AccountProcessor accountProcessor;

    private final TransactionProcessor transactionProcessor;

    private static final Logger logger = Logger.getLogger(AccountService.class);

    /**
     * Constructor.
     *
     * @param accountProcessor DAO for accounts.
     * @param transactionProcessor DAO for transactions.
     */
    public AccountService(AccountProcessor accountProcessor, TransactionProcessor transactionProcessor) {
        this.accountProcessor = accountProcessor;
        this.transactionProcessor = transactionProcessor;
    }

    /**
     * Performs a transaction between two accounts.
     *
     * @param payeeId the ID of the payee account.
     * @param recipientId the ID of the recipient account.
     * @param payment the quantity to pay.
     * @return true if the payment occurs successfully.
     * @throws Exception if an error occurs saving the transaction
     */
    @NonNull
    public boolean performTransaction(final Long payeeId, final Long recipientId, final Double payment) throws Exception {
        final Transaction transaction = new Transaction(payeeId, recipientId, payment);
        final Optional<Account> payee = accountProcessor.findById(payeeId);
        final Optional<Account> recipient = accountProcessor.findById(recipientId);

        if (payee.isPresent() && recipient.isPresent()) {
            return executeTransaction(payee.get(), recipient.get(), transaction);
        } else {
            final ObjectNotFoundException exception = new ObjectNotFoundException("One or more users involved in this transaction do not exist. " + payee.isPresent() + " " + recipient.isPresent());
            logger.error(exception);
            throw exception;
        }
    }

    /**
     * Finish the transaction between accounts.
     *
     * @param payee the payee account.
     * @param recipient the recipient account.
     * @param transaction the transaction to complete.
     * @return true if completion is a success.
     * @throws Exception if the transaction fails to commit to postgres.
     */
    @NonNull
    private boolean executeTransaction(final Account payee, final Account recipient, final Transaction transaction) throws Exception {
        if (recipient.canPay(transaction.getPayment())) {
            recipient.deduct(transaction.getPayment());
            payee.payIn(transaction.getPayment());
            transaction.complete(true);
            transactionProcessor.save(transaction);
            return true;
        } else return false;
    }

    /**
     * Generate an account statement for the account denoted by id
     *
     * @param id the account ID.
     * @return the newly generated account statement.
     * @throws ObjectNotFoundException if the account does not exist.
     */
    @NonNull
    public Statement generateAccountStatement(final Long id) throws ObjectNotFoundException {
        final Optional<Account> account = accountProcessor.findById(id);
        if (account.isPresent()) {
            final Account theAccount = account.get();
            final Statement statement = new Statement(theAccount.getBalance(), theAccount.getId());
            final List<Transaction> transactionList = new ArrayList<>();
            transactionList.addAll(transactionProcessor.findTransactionsId(id));
            transactionList.sort(Comparator.comparing(Transaction::getDate));
            statement.addTransactions(transactionList);
            return statement;
        } else {
            final ObjectNotFoundException exception = new ObjectNotFoundException("Account with ID: " + id + "does not exist");
            logger.error(exception);
            throw exception;
        }
    }
}
