package com.example.transactions.Service;

import com.example.transactions.Entity.Transaction;
import com.example.transactions.Request.TransactionRequest;
import com.example.transactions.Response.TransactionResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface TransactionService {

    TransactionResponse findTransactionById(Long id);

    List<TransactionResponse> findAllTransactionsByAccount(int accountId);

    TransactionResponse createTransaction(TransactionRequest transactionRequest);

}
