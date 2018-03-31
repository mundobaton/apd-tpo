package edu.uade.apd.tpo.service;

import edu.uade.apd.tpo.model.Cliente;

public interface ClienteService {

    public Cliente login(Long cuil, String password);
}
