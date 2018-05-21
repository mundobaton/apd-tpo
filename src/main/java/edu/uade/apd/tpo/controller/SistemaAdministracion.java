package edu.uade.apd.tpo.controller;

import edu.uade.apd.tpo.dao.ClienteDao;
import edu.uade.apd.tpo.dao.UsuarioDao;
import edu.uade.apd.tpo.entity.UsuarioEntity;
import edu.uade.apd.tpo.exception.BusinessException;
import edu.uade.apd.tpo.model.Cliente;
import edu.uade.apd.tpo.model.CondIva;
import edu.uade.apd.tpo.model.CuentaCorriente;
import edu.uade.apd.tpo.model.Domicilio;
import edu.uade.apd.tpo.model.Usuario;
import edu.uade.apd.tpo.model.ZonaEnvio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    public Usuario login(String email, String password) throws BusinessException {
        Usuario u = this.buscarUsuario(email);
        if (u != null && u.getPassword().equals(password)) {
            return u;
        }
        throw new BusinessException("El usuario no existe o la contraseña es incorrecta");
    }

    private Usuario buscarUsuario(String email) {
        UsuarioEntity entity = usuarioDao.findByEmail(email);
        return entity == null ? null : Usuario.fromEntity(entity);
    }

    public Cliente crearCliente(String email, String password, String nombre, long cuil, String telefono, CondIva condIva, String calle, int numero, String codPostal, String localidad, String provincia, ZonaEnvio zona, float saldo, float limiteCredito) throws BusinessException {
        if (clienteDao.findByEmail(email) != null) {
            throw new BusinessException("El cliente con email '" + email + "' ya existe");
        }

        logger.debug("Creando cliente...");
        Cliente cliente = new Cliente();
        cliente.setEmail(email);
        cliente.setPassword(password);
        cliente.setNombre(nombre);
        cliente.setCuil(cuil);
        cliente.setTelefono(telefono);
        cliente.setCondIva(condIva);

        Domicilio domicilio = new Domicilio();
        domicilio.setCalle(calle);
        domicilio.setNumero(numero);
        domicilio.setCodPostal(codPostal);
        domicilio.setLocalidad(localidad);
        domicilio.setProvincia(provincia);
        domicilio.setZona(zona);
        cliente.setDomicilio(domicilio);

        CuentaCorriente cuentaCorriente = new CuentaCorriente();
        cuentaCorriente.setSaldo(saldo);
        cuentaCorriente.setLimiteCredito(limiteCredito);
        cliente.setCuentaCorriente(cuentaCorriente);

        cliente.guardar();
        logger.debug("Cliente creado exitosamente...");

        return cliente;
    }

}
