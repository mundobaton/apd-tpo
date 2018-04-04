package edu.uade.apd.tpo.service.impl;

import edu.uade.apd.tpo.model.Cliente;
import edu.uade.apd.tpo.model.Domicilio;
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
}
