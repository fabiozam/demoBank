package com.example.account.Controller;

import com.example.account.AccountApplication;
import com.example.account.Entity.Account;
import com.example.account.Request.AddAccountRequest;
import com.example.account.Service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/account")
public class AccountController {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(AccountApplication.class);

    @Autowired
    private AccountService accountService;

    @Operation(summary = "Creates new account for a client", description = "Creates new account.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully created"),
    })
    @PostMapping("/newAccount")
    public Account newAccount(@RequestBody AddAccountRequest account) {
        log.info("New account request: {}", account);
        return accountService.addAccount(account);
    }

    @Operation(summary = "Gets account by number", description = "Gets account by number.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Account not found"),
    })
    @GetMapping("/{id}")
    public Account getAccountById(@PathVariable Long id) {
        log.info("Get account by id: {}", id);
        return accountService.getAccount(id).orElseThrow();
        //return accountRepository.findById(id).orElseThrow();
    }

    @Operation(summary = "Get accounts by client", description = "Get accounts by client.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Accounts not found"),
    })
    @GetMapping("/cliente/{numeroCedula}")
    public List<Account> getAccountByCedula(@PathVariable int numeroCedula) {
        log.info("Get accounts by client: {}", numeroCedula);
        return accountService.getAccountsByClient(numeroCedula);
    }

    @Operation(summary = "Verify account by ID", description = "Verifies if an account exists by ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully processed"),
    })
    @GetMapping("/exist/{id}")
    public boolean accountExist(@PathVariable Long id) {
        log.info("Verify account by ID: {}", id);
        return accountService.accountExists(id);
    }

    @Operation(summary = "Updates an account", description = "Updates an account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully processed"),
    })
    @PostMapping("/update")
    public ResponseEntity<Account> updateAccount(@RequestBody Account account) {
        log.info("Updates an account: {}", account);
        accountService.saveAccount(account);
        return ResponseEntity.ok(account);
    }

}
