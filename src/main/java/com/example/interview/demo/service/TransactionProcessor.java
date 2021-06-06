package com.example.interview.demo.service;

import com.example.interview.demo.model.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

public interface TransactionProcessor extends CrudRepository<Transaction, Long> {
}
