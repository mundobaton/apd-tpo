package edu.uade.apd.tpo.service.impl;

import edu.uade.apd.tpo.model.Cliente;
import edu.uade.apd.tpo.service.ClienteService;

import javax.inject.Singleton;

@Singleton
public class ClienteServiceImpl implements ClienteService {

    @Override
    public Cliente login(Long cuil, String password) {
        Cliente cliente = new Cliente();
        cliente.setNombre("Agustin");
        return cliente;
    }
}
