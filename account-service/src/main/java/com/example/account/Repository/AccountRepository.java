package com.example.account.Repository;

import com.example.account.Entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query("select a from Account a where a.cliente.cedula = ?1")
    List<Account> findAccountByClientId(int clientId);

}
