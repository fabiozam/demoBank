package com.example.account.Service;

import com.example.account.Entity.Account;
import com.example.account.Request.AddAccountRequest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Component
public interface AccountService{

    Account addAccount(AddAccountRequest account);

    Optional<Account> getAccount(Long numeroCuenta);

    List<Account> getAccountsByClient(int numeroCedula);

    Boolean accountExists(Long numeroCuenta);

    void saveAccount(Account account);

}
