package edu.uade.apd.tpo.service.impl;

import edu.uade.apd.tpo.model.Cliente;
import edu.uade.apd.tpo.model.Domicilio;
import edu.uade.apd.tpo.service.UsuarioService;

import javax.inject.Singleton;

@Singleton
public class UsuarioServiceImpl implements UsuarioService {

    @Override
    public Cliente login(String email, String password) {
        Cliente cliente = new Cliente();
        cliente.setNombre("Agustin");
        Domicilio dom = new Domicilio();
        dom.setCalle("asd");
        cliente.setDomicilio(dom);
        return cliente;
    }
}
