package com.example.transactions.Controller;

import com.example.transactions.Entity.Transaction;
import com.example.transactions.Request.TransactionRequest;
import com.example.transactions.Response.TransactionResponse;
import com.example.transactions.Service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(TransactionController.class);

    @Operation(summary = "Get transaction by ID", description = "Get transaction by ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Account not found")
    })
    @GetMapping("/{id}")
    public TransactionResponse getTransaction(@PathVariable Long id) {
        log.info("Get transaction by ID: {}", id);
        return transactionService.findTransactionById(id);
    }

    @Operation(summary = "Get transaction by Account", description = "Get all transactions by AccountID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Account not found")
    })
    @GetMapping("/byAccount/{accountId}")
    public List<TransactionResponse> getTransactionByAccountId(@PathVariable int accountId) {
        log.info("Get transaction by AccountID: {}", accountId);
        return transactionService.findAllTransactionsByAccount(accountId);
    }

    @Operation(summary = "Create new transaction", description = "Create new transaction.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved")
    })
    @PostMapping("/newTransaction")
    public ResponseEntity<TransactionResponse> createTransaction(@RequestBody TransactionRequest transactionRequest) {
        log.info("Create transaction: {}", transactionRequest);
        TransactionResponse transactionResponse = transactionService.createTransaction(transactionRequest);
        return ResponseEntity.ok(transactionResponse);
    }

}
