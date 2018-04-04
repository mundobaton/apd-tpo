package edu.uade.apd.tpo.service;

import edu.uade.apd.tpo.model.Usuario;

public interface UsuarioService {

    Usuario login(String email, String password);
}
