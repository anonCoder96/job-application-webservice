package com.example.interview.demo.service;

import com.example.interview.demo.model.Transaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface TransactionProcessor extends CrudRepository<Transaction, Long> {

    @Query(value = "SELECT * FROM transaction t WHERE t.payee_id = ?1 OR t.recipient_id = ?1", nativeQuery = true)
    Collection<Transaction> findTransactionsId(long id);
}
