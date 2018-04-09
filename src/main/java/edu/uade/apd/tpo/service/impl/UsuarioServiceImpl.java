package edu.uade.apd.tpo.service.impl;

import edu.uade.apd.tpo.model.Cliente;
import edu.uade.apd.tpo.model.CondIva;
import edu.uade.apd.tpo.model.Domicilio;
import edu.uade.apd.tpo.model.Rol;
import edu.uade.apd.tpo.model.Usuario;
import edu.uade.apd.tpo.service.UsuarioService;

import javax.inject.Singleton;

@Singleton
public class UsuarioServiceImpl implements UsuarioService {

    @Override
    public Usuario login(String email, String password) {
        Usuario usuario = new Usuario();
        usuario.setEmail("asd@email.com");
        usuario.setPassword("un password seguro");
        return usuario;
    }

    @Override
    public void crearUsuario(String email, String password, Rol rol) {
        //TODO
    }

    @Override
    public Cliente crearCliente(String email, String password, Long cuil, String nombre, Domicilio domicilio, CondIva condIva) {
        Cliente cli = new Cliente();
        cli.setEmail(email);
        cli.setPassword(password);
        cli.setCuil(cuil);
        cli.setNombre(nombre);
        cli.setDomicilio(domicilio);
        cli.setCondIva(condIva);
        //TODO persistir cliente
        return cli;
    }
}
