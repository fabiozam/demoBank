package com.example.account.Controller;

import com.example.account.AccountApplication;
import com.example.account.Entity.Client;
import com.example.account.Repository.ClientRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clientes")
public class ClientController {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(AccountApplication.class);

    private final ClientRepository clientRepository;

    public ClientController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Operation(summary = "Get all clients", description = "Get all clients information.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved")
    })
    @GetMapping
    public Iterable<Client> getClients() {
        log.info("Get all clients");
        return clientRepository.findAll();
    }


}
