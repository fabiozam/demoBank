package com.example.account.Service.Impl;

import com.example.account.AccountApplication;
import com.example.account.Entity.Account;
import com.example.account.Entity.AccountType;
import com.example.account.Entity.Client;
import com.example.account.Repository.AccountRepository;
import com.example.account.Repository.AccountTypeRepository;
import com.example.account.Repository.ClientRepository;
import com.example.account.Request.AddAccountRequest;
import com.example.account.Service.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AccountTypeRepository accountTypeRepository;
    @Autowired
    private ClientRepository clientRepository;

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(AccountApplication.class);

    @Override
    public Account addAccount(AddAccountRequest accountReq) {
        Account account = new Account();
        AccountType accountType = accountTypeRepository.findById(accountReq.getTipoCuenta())
                .orElseThrow(() -> new NoSuchElementException("Tipo Cuenta: " + accountReq.getTipoCuenta()));
        Client client = clientRepository.findById(accountReq.getCliente())
                .orElseThrow(() -> new NoSuchElementException("Cedula: " + accountReq.getCliente()));
        account.setCliente(client);
        account.setTipoCuenta(accountType);
        account.setFechaCreacion(accountReq.getFechaCreacion());
        account.setFechaUltimaModificacion(accountReq.getFechaUltimaModificacion());
        account.setSaldo(accountReq.getSaldo());
        return accountRepository.save(account);
    }

    @Override
    public Optional<Account> getAccount(Long numeroCuenta) {
        return accountRepository.findById(numeroCuenta);
    }

    @Override
    public List<Account> getAccountsByClient(int numeroCedula) {
        List<Account> accounts = accountRepository.findAccountByClientId(numeroCedula);
        if(accounts.isEmpty()) {
            throw new NoSuchElementException("Cedula: " + numeroCedula);
        }
        return accounts;
    }

    @Override
    public Boolean accountExists(Long numeroCuenta) {
        return accountRepository.existsById(numeroCuenta);
    }

    @Override
    public void saveAccount(Account account) {
        accountRepository.save(account);
    }

}
