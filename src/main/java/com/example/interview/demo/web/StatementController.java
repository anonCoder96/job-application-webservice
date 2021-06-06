package com.example.interview.demo.web;

import com.example.interview.demo.service.AccountService;
import com.example.interview.demo.service.Statement;
import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Statement rest controller, generates statements for accounts.
 */
@RestController
public class StatementController {

    /**
     * The account service used to generate statements for accounts.
     */
    private AccountService accountService;

    /**
     * Constructor.
     *
     * @param accountService the account service used to generate statements for accounts.
     */
    public StatementController(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * Generates and returns an account statement for the account with ID denoted by id.
     *
     * @param id the account ID number.
     * @return a statement containing ID, balance, and ingoing/outgoing transactions.
     */
    @GetMapping("/statement/{id}")
    @ResponseBody
    public Statement getAccountStatement(@PathVariable("id") long id) throws ObjectNotFoundException {
        return accountService.generateAccountStatement(id);
    }
}
