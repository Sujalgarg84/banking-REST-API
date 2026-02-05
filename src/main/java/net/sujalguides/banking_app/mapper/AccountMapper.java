package net.sujalguides.banking_app.mapper;

import net.sujalguides.banking_app.dto.AccountDto;
import net.sujalguides.banking_app.entity.Account;

public class AccountMapper {

    // Dto  -> Account Entity
    public static Account mapToAccount(AccountDto accountDto){
        Account account = new Account(
                accountDto.getId(),
                accountDto.getAccountHolderName(),
                accountDto.getBalance()
        );
        return account;
    }

    // Entity -> Dto
    public static AccountDto mapToAccountAto(Account account){
        AccountDto accountDto = new AccountDto(
                account.getId(),
                account.getAccountHolderName(),
                account.getBalance()
        );

        return accountDto;
    }

}
