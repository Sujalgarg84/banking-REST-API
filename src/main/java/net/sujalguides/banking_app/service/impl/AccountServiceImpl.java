package net.sujalguides.banking_app.service.impl;

import net.sujalguides.banking_app.dto.AccountDto;
import net.sujalguides.banking_app.entity.Account;
import net.sujalguides.banking_app.mapper.AccountMapper;
import net.sujalguides.banking_app.repository.AccountRepository;
import net.sujalguides.banking_app.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        // Dto -> Entity
        Account account = AccountMapper.mapToAccount(accountDto);
        // saved Entity
        Account savedAccount = accountRepository.save(account);
        // Entity -> Dto
        return AccountMapper.mapToAccountAto(savedAccount);
    }

    @Override
    public AccountDto getAccountById(Long id) {

        Account account = accountRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Account does not exists"));

        return AccountMapper.mapToAccountAto(account);
    }

    @Override
    public AccountDto deposit(Long id, double amount) {

       Account account = accountRepository.findById(id).orElseThrow(
               () -> new RuntimeException("Account does not exists."));

       double total = account.getBalance() + amount;
       account.setBalance(total);
       Account savedAccount = accountRepository.save(account);
       return AccountMapper.mapToAccountAto(savedAccount);
    }

    @Override
    public AccountDto withdraw(Long id, double amount) {

        Account account = accountRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Account not Exists"));

        if(account.getBalance() < amount){
            throw new RuntimeException("Insufficient amount");
        }

        double total = account.getBalance() - amount;
        account.setBalance(total);
        Account savedAccount = accountRepository.save(account);

        return AccountMapper.mapToAccountAto(savedAccount);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream().map(account -> AccountMapper.mapToAccountAto(account)).collect(Collectors.toList());

    }

    @Override
    public void deleteAccount(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account does not Exists."));

        accountRepository.deleteById(id);
    }
}
