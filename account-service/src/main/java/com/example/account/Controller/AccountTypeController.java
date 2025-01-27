package com.example.account.Controller;

import com.example.account.AccountApplication;
import com.example.account.Entity.AccountType;
import com.example.account.Repository.AccountTypeRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accountType")
public class AccountTypeController {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(AccountApplication.class);

    private final AccountTypeRepository accountTypeRepository;

    public AccountTypeController(AccountTypeRepository accountTypeRepository) {
        this.accountTypeRepository = accountTypeRepository;
    }

    @Operation(summary = "Get all account types", description = "Get all account types information.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved")
    })
    @GetMapping
    public Iterable<AccountType> getAllAccountTypes() {
        return accountTypeRepository.findAll();
    }

}
