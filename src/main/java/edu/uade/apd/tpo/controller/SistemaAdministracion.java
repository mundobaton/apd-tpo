package edu.uade.apd.tpo.controller;

import edu.uade.apd.tpo.dao.impl.ClienteDao;
import edu.uade.apd.tpo.dao.impl.UsuarioDao;
import edu.uade.apd.tpo.entity.UsuarioEntity;
import edu.uade.apd.tpo.model.Cliente;
import edu.uade.apd.tpo.model.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class SistemaAdministracion {

    private static SistemaAdministracion instance;
    private UsuarioDao usuarioDao;
    private ClienteDao clienteDao;
    private static final Logger logger = LoggerFactory.getLogger(SistemaAdministracion.class);

    private SistemaAdministracion() {
        this.usuarioDao = UsuarioDao.getInstance();
        this.clienteDao = ClienteDao.getInstance();
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

    public List<Cliente> getClientes() {
        return clienteDao.findAll();
    }

    public void crearUsuario(String email, String password) {
        logger.debug("Creando usuario...");
        Usuario u = new Usuario();
        u.setEmail(email);
        u.setPassword(password);
        u.guardar();
        logger.debug("Usuario creado exitosamente...");
    }

    public void actualizarUsuario(Usuario u) {
        logger.debug("Modificando usuario...");
        u.modificar();
        logger.debug("Usuario modificado exitosamente...");
    }

    public void crearCliente(String email, String password, String nombre, long cuil, String telefono) {
        logger.debug("Creando cliente...");
        Cliente cliente = new Cliente();
        cliente.setEmail(email);
        cliente.setPassword(password);
        cliente.setNombre(nombre);
        cliente.setCuil(cuil);
        cliente.setTelefono(telefono);
        cliente.guardar();
        logger.debug("Cliente creado exitosamente...");
    }


}
