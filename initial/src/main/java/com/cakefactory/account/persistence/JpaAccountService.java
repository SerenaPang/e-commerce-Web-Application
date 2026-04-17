package com.cakefactory.account.persistence;

import com.cakefactory.account.Account;
import com.cakefactory.account.AccountService;
import org.springframework.stereotype.Component;

@Component
class JpaAccountService implements AccountService {

    private final AccountRepository accountRepository;

    JpaAccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void register(String email, String password) {
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setEmail(email);
        accountEntity.setPassword(password);

        this.accountRepository.save(accountEntity);
    }

    @Override
    public Account find(String email) {
        AccountEntity accountEntity = this.accountRepository.findByEmail(email);
        return new Account(accountEntity.getEmail(), accountEntity.getPassword());
    }

    @Override
    public boolean exists(String email) {
        return this.accountRepository.findByEmail(email) != null;
    }
}
