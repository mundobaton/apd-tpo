package edu.uade.apd.tpo.controller;

import edu.uade.apd.tpo.model.Usuario;
import edu.uade.apd.tpo.service.UsuarioService;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class SistemaAdministracion {

    private UsuarioService usuarioService;

    @Inject
    public SistemaAdministracion(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public Usuario login(String email, String password) {
        return usuarioService.login(email, password);
    }
}
