package com.pichincha.account.service;

import java.util.List;

import com.pichincha.account.model.Account;

public interface AccountService {
    Account saveAccount(Account account);
    
    Account updateAccount(Account account,String accountNumber);

    Account getAccountById(String accountNumber);

    List<Account> getAllAccounts();

    void deleteAccountById(String accountNumber);
}
