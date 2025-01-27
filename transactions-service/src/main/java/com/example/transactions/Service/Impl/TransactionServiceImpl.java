package com.example.transactions.Service.Impl;

import com.example.transactions.Entity.Transaction;
import com.example.transactions.Entity.TransactionType;
import com.example.transactions.Repository.TransactionRepository;
import com.example.transactions.Request.TransactionRequest;
import com.example.transactions.Response.AccountResponse;
import com.example.transactions.Response.TransactionResponse;
import com.example.transactions.Response.TransactionTypeResponse;
import com.example.transactions.Service.TransactionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class TransactionServiceImpl implements TransactionService {

    private String token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJzb2Z0dGVrSldUIiwic3ViIjoiZmFiaW8iLCJhdXRob3JpdGllcyI6WyJST0xFX1VTRVIiXSwiaWF0IjoxNzM3OTY4NDUwLCJleHAiOjE3Mzc5NjkwNTB9.bx_7khFmKHj28i3f1klntBSYbMuo1Oza0GRkjX6W4WI";

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(TransactionServiceImpl.class);

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public TransactionResponse findTransactionById(Long id) {
        Optional<Transaction> transaction = transactionRepository.findById(id);
        return populateObject(transaction.get());
    }

    @Override
    public List<TransactionResponse> findAllTransactionsByAccount(int accountId) {
        if(!accountExists(accountId)) {
            log.error("Numero de cuenta {} no existe.", accountId);
            throw new NoSuchElementException("Cuenta: " + accountId);
        }
        List<TransactionResponse> transactionResponseList = new ArrayList<>();

        for(Transaction t : transactionRepository.findByCuentaOrigen(accountId)){
            transactionResponseList.add(populateObject(t));
        }

        return transactionResponseList;
    }

    @Override
    public TransactionResponse createTransaction(TransactionRequest transactionRequest) {
        if(!accountExists(transactionRequest.getCuentaDestino())) {
            log.error("Cuenta Destino {} no existe.", transactionRequest.getCuentaDestino());
            throw new NoSuchElementException("Cuenta Destino: " + transactionRequest.getCuentaDestino());
        }
        if(!accountExists(transactionRequest.getCuentaOrigen())){
            log.error("Cuenta Origen {} no existe.", transactionRequest.getCuentaOrigen());
            throw new NoSuchElementException("Cuenta Origen: " + transactionRequest.getCuentaOrigen());
        }

        AccountResponse accountResponseOrigen = getAccountFromAPIById(transactionRequest.getCuentaOrigen());
        AccountResponse accountResponseDestino = getAccountFromAPIById(transactionRequest.getCuentaDestino());

        int saldoOrigen = accountResponseOrigen.getSaldo();
        int saldoDestino = accountResponseDestino.getSaldo();

        if(transactionRequest.getTipoTransaccion() == 1 || transactionRequest.getTipoTransaccion() == 3) {
            if (saldoOrigen < transactionRequest.getMonto()) {
                log.error("Saldo insuficiente para realizar la transaccion. Cuenta: {}", transactionRequest.getCuentaDestino());
                throw new NoSuchElementException("Saldo insuficiente para realizar la transaccion");
            }
        }

        accountResponseOrigen.setSaldo(saldoOrigen - transactionRequest.getMonto());
        accountResponseDestino.setSaldo(saldoDestino + transactionRequest.getMonto());

        accountResponseOrigen.setFechaUltimaModificacion(new Date());
        accountResponseDestino.setFechaUltimaModificacion(new Date());

        try {
            accountResponseOrigen = updateAccount(accountResponseOrigen);
            accountResponseDestino = updateAccount(accountResponseDestino);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        Transaction transaction = new Transaction();

        transaction.setCuentaDestino(accountResponseDestino.getNumeroCuenta());
        transaction.setCuentaOrigen(accountResponseOrigen.getNumeroCuenta());
        transaction.setMonto(transactionRequest.getMonto());
        transaction.setFechaTransaccion(new Date());
        TransactionType transactionType = new TransactionType();
        transactionType.setId(transactionRequest.getTipoTransaccion());
        transaction.setTipoTransaccion(transactionType);

        transactionRepository.save(transaction);

        log.info("Transacction procesada con éxito.");

        return populateObject(transaction);
    }

    private TransactionResponse populateObject(Transaction transaction){
        log.info("Populating TransactionResponse object");
        TransactionResponse transactionResponse = new TransactionResponse();

        AccountResponse accountResponseOrigen = getAccountFromAPIById(transaction.getCuentaOrigen());
        AccountResponse accountResponseDestino = getAccountFromAPIById(transaction.getCuentaDestino());

        transactionResponse.setId(transaction.getId());
        transactionResponse.setCuentaDestino(accountResponseDestino);
        transactionResponse.setCuentaOrigen(accountResponseOrigen);
        transactionResponse.setMonto(transaction.getMonto());
        transactionResponse.setFechaTransaccion(transaction.getFechaTransaccion());
        TransactionTypeResponse transactionTypeResponse = new TransactionTypeResponse();
        transactionTypeResponse.setDescripcion(transaction.getTipoTransaccion().getDescripcion());
        transactionResponse.setTipoTransaccion(transactionTypeResponse);

        return transactionResponse;
    }

    private AccountResponse getAccountFromAPIById(int id) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<String> entity = new HttpEntity<String>("Some body", headers);
        ResponseEntity<AccountResponse> response = restTemplate.exchange("http://localhost:8082/account/" + id, HttpMethod.GET, entity, AccountResponse.class);
        return response.getBody();
    }

    private Boolean accountExists(int accountId) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<String> entity = new HttpEntity<String>("Some body", headers);
        ResponseEntity<Boolean> response = restTemplate.exchange("http://localhost:8082/account/exist/" + accountId, HttpMethod.GET, entity, Boolean.class);
        return response.getBody();
    }

    private AccountResponse updateAccount(AccountResponse account) throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(account);
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);
        HttpEntity<String> entity = new HttpEntity<String>(json, headers);
        ResponseEntity<AccountResponse> response = restTemplate.exchange("http://localhost:8082/account/update", HttpMethod.POST, entity, AccountResponse.class);
        return response.getBody();
    }
}
