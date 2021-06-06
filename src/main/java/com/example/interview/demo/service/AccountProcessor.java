package com.example.interview.demo.service;

import com.example.interview.demo.model.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountProcessor extends CrudRepository<Account, Long> {
}
