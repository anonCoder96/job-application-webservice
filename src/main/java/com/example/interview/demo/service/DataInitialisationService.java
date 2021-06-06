package com.example.interview.demo.service;

import com.example.interview.demo.model.Account;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

/**
 * Pre populate the database with data - just used for demo purposes.
 */
@Service
public class DataInitialisationService {

    private final AccountProcessor accountProcessor;

    private final TransactionProcessor transactionProcessor;

    public DataInitialisationService(AccountProcessor accountProcessor, TransactionProcessor transactionProcessor) {
        this.accountProcessor = accountProcessor;
        this.transactionProcessor = transactionProcessor;
    }

    @PostConstruct
    @Transactional
    public void init() {
        Account accountOne = new Account(2209.29);
        Account accountTwo = new Account(3569.00);
        accountProcessor.save(accountOne);
        accountProcessor.save(accountTwo);
    }
}
