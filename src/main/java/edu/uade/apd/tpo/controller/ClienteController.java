package edu.uade.apd.tpo.controller;

import edu.uade.apd.tpo.model.Cliente;
import edu.uade.apd.tpo.service.ClienteService;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.Optional;

@Singleton
public class ClienteController {

    private ClienteService clienteService;

    @Inject
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    public Cliente login(final Long cuil, String password) {
        return clienteService.login(cuil, password);
    }
}
