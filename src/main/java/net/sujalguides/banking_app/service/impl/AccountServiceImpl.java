package net.sujalguides.banking_app.service.impl;

import net.sujalguides.banking_app.dto.AccountDto;
import net.sujalguides.banking_app.entity.Account;
import net.sujalguides.banking_app.mapper.AccountMapper;
import net.sujalguides.banking_app.repository.AccountRepository;
import net.sujalguides.banking_app.service.AccountService;
import org.springframework.stereotype.Service;

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
}
