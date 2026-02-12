package net.sujalguides.banking_app.service;

import net.sujalguides.banking_app.dto.AccountDto;

import java.util.List;
//import net.sujalguides.banking_app.entity.Account;

public interface AccountService {

    AccountDto createAccount(AccountDto accountDto);

    AccountDto getAccountById(Long id);

    AccountDto deposit(Long id, double amount);

    AccountDto withdraw(Long id, double amount);

    List<AccountDto> getAllAccounts();

    // use void it doesn't return anything only performs operation.
    void deleteAccount(Long id);

}
