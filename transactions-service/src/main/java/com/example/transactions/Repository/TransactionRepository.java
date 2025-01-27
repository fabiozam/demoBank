package com.example.transactions.Repository;

import com.example.transactions.Entity.Transaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Long> {

    @Query("select t from Transaction t where t.cuentaOrigen = ?1")
    List<Transaction> findByCuentaOrigen(int cuentaOrigen);

}
