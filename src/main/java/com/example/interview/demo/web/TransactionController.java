package com.example.interview.demo.web;

import com.example.interview.demo.service.AccountService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest controller for endpoints regarding transactions.
 */
@RestController
public class TransactionController {

    /**
     * The account service used to perform transactions on accounts.
     */
    private AccountService accountService;

    /**
     * Constructor.
     *
     * @param accountService the account service used to perform transactions on accounts.
     */
    public TransactionController(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * Endpoint to perform a transaction between two accounts.
     *
     * @param payeeId The ID of the payee account.
     * @param recipientId the ID of the recipient account.
     * @param amount the amount of money to pay.
     * @return true if the transaction is completed successfully.
     * @throws Exception if an error occurs in processing.
     */
    @PostMapping("/transaction/pay/{payeeId}/{recipientId}/{amount}")
    @ResponseBody
    public boolean payUser(@PathVariable("payeeId") final long payeeId,
                           @PathVariable("recipientId") final long recipientId,
                           @PathVariable("amount") final double amount) throws Exception {
        return accountService.performTransaction(payeeId, recipientId, amount);
    }

}
