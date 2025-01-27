package com.example.transactions.Repository;

import com.example.transactions.Entity.TransactionType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionTypeRepository extends CrudRepository<TransactionType, Long> {
}
