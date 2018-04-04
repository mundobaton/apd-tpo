package edu.uade.apd.tpo.service;

import edu.uade.apd.tpo.model.CondIva;
import edu.uade.apd.tpo.model.Domicilio;
import edu.uade.apd.tpo.model.Rol;
import edu.uade.apd.tpo.model.Usuario;

public interface UsuarioService {

    Usuario login(String email, String password);

    void crearUsuario(String email, String password, Rol rol);

    void crearCliente(String email, String password, Long cuil, String nombre, String apellido, Domicilio domicilio, CondIva condIva);
}
