package edu.uade.apd.tpo.controller;

import edu.uade.apd.tpo.repository.model.Cliente;

import java.util.List;
import java.util.Optional;

public class ClienteController {

    private static ClienteController instance;
    private List<Cliente> clientes;

    private ClienteController() {
        //TODO inicializar listado clientes
    }

    public static ClienteController getInstance() {
        if (instance == null) {
            instance = new ClienteController();
        }
        return instance;
    }


    public Cliente login(final Long cuil, String password) {
        /*
        Cliente cliente = this.buscarClientePorCuil(cuil);
        if (cliente.getPassword().equals(password)) {
            return cliente;
        }
        //TODO throw specific exception
        return null;
        */
        Cliente cliente = new Cliente();
        cliente.setNombre("Agustin");
        cliente.setCuil(123321L);
        return cliente;
    }

    private Cliente buscarClientePorCuil(Long cuil) {
        Optional<Cliente> cliente = clientes.stream().filter(c -> c.getCuil() == cuil).findFirst();
        if (cliente.isPresent()) {
            return cliente.get();
        }
        return null;
    }

}
