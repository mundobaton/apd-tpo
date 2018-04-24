package edu.uade.apd.tpo.controller;

import edu.uade.apd.tpo.dao.impl.UsuarioDao;
import edu.uade.apd.tpo.entity.UsuarioEntity;
import edu.uade.apd.tpo.model.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class SistemaAdministracion {

    private static SistemaAdministracion instance;
    private UsuarioDao usuarioDao;
    private static final Logger logger = LoggerFactory.getLogger(SistemaAdministracion.class);

    private SistemaAdministracion() {
        this.usuarioDao = UsuarioDao.getInstance();
    }

    public static SistemaAdministracion getInstance() {
        if (instance == null) {
            instance = new SistemaAdministracion();
        }
        return instance;
    }

    public List<Usuario> getUsuarios() {
        return usuarioDao.findAll();
    }

    public void crearUsuario(String email, String password) {
        logger.debug("Creando usuario...");
        Usuario u = new Usuario();
        u.setEmail(email);
        u.setPassword(password);
        u.guardar();
        logger.debug("Usuario creado exitosamente...");
    }


}
