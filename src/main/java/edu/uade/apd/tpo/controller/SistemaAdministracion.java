package edu.uade.apd.tpo.controller;

import edu.uade.apd.tpo.dao.ClienteDao;
import edu.uade.apd.tpo.dao.PedidoDao;
import edu.uade.apd.tpo.dao.UsuarioDao;
import edu.uade.apd.tpo.entity.ClienteEntity;
import edu.uade.apd.tpo.entity.PedidoEntity;
import edu.uade.apd.tpo.entity.UsuarioEntity;
import edu.uade.apd.tpo.exception.ArticulosFaltantesException;
import edu.uade.apd.tpo.exception.BusinessException;
import edu.uade.apd.tpo.model.Articulo;
import edu.uade.apd.tpo.model.Cliente;
import edu.uade.apd.tpo.model.CondIva;
import edu.uade.apd.tpo.model.CuentaCorriente;
import edu.uade.apd.tpo.model.Domicilio;
import edu.uade.apd.tpo.model.EstadoPedido;
import edu.uade.apd.tpo.model.OrdenCompra;
import edu.uade.apd.tpo.model.Pedido;
import edu.uade.apd.tpo.model.Usuario;
import edu.uade.apd.tpo.model.ZonaEnvio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class SistemaAdministracion {

    private static SistemaAdministracion instance;
    private UsuarioDao usuarioDao;
    private ClienteDao clienteDao;
    private PedidoDao pedidoDao;
    private static final Logger logger = LoggerFactory.getLogger(SistemaAdministracion.class);

    private SistemaAdministracion() {
        this.usuarioDao = UsuarioDao.getInstance();
        this.clienteDao = ClienteDao.getInstance();
        this.pedidoDao = PedidoDao.getInstance();
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

    private Cliente buscarCliente(String email) {
        ClienteEntity entity = clienteDao.findByEmail(email);
        return entity == null ? null : Cliente.fromEntity(entity);
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

        cliente = cliente.guardar();
        logger.debug("Cliente creado exitosamente...");

        return cliente;
    }

    public Pedido generarPedido(String email, String calle, int num, String cp, String loc, String prov, ZonaEnvio zona) throws BusinessException {
        logger.info("Generando pedido...");
        Cliente cli = buscarCliente(email);
        if (cli == null) {
            throw new BusinessException("El cliente con email '" + email + "' no existe");
        }

        Pedido pedido = new Pedido();
        pedido.setCliente(cli);
        pedido.setFechaPedido(new Date());
        Domicilio dom = new Domicilio();
        dom.setCalle(calle);
        dom.setNumero(num);
        dom.setCodPostal(cp);
        dom.setLocalidad(loc);
        dom.setProvincia(prov);
        dom.setZona(zona);
        pedido.setDomicilio(dom);
        pedido.iniciar();
        pedido = pedido.guardar();
        logger.info("Pedido generado exitosamente...");
        return pedido;
    }

    public void agregarItemPedido(Integer pedidoId, Integer articuloId, int cantidad) throws BusinessException {
        logger.info("Agregando items al pedido...");
        PedidoEntity entity = this.pedidoDao.findById(pedidoId);
        if (entity == null) {
            throw new BusinessException("El pedido '" + pedidoId + "' no existe");
        }
        Pedido p = Pedido.fromEntity(entity);
        if (p.getEstado() != EstadoPedido.INICIADO) {
            throw new BusinessException("El pedido debe estar iniciado!, estado actual '" + p.getEstado() + "'");
        }
        Articulo articulo = SistemaDeposito.getInstance().buscarArticulo(articuloId);
        p.agregarItem(articulo, cantidad);
        p.guardar();
        logger.info("Item agregado exitosamente!");
    }

    public void finalizarCargaItems(Integer pedidoId) throws BusinessException {
        logger.info("Finalizando carga de items al pedido...");
        PedidoEntity entity = this.pedidoDao.findById(pedidoId);
        if (entity == null) {
            throw new BusinessException("El pedido '" + pedidoId + "' no existe");
        }
        Pedido p = Pedido.fromEntity(entity);
        p.cerrar();
        p.guardar();
        logger.info("Fin de carga de items exitoso!");
    }

    public void aprobarPedido(Integer pedidoId) throws BusinessException {
        logger.info("Aprobando pedido...");
        PedidoEntity entity = this.pedidoDao.findById(pedidoId);
        if (entity == null) {
            throw new BusinessException("El pedido '" + pedidoId + "' no existe");
        }
        Pedido p = Pedido.fromEntity(entity);
        if (p.getEstado() != EstadoPedido.PENDIENTE) {
            throw new BusinessException("El estado del pedido debe ser PENDIENTE, actual '" + p.getEstado() + "'");
        }
        try {
            p.aprobar();
            logger.info("Pedido aprobado exitosamente y completado!");
        } catch (ArticulosFaltantesException afe) {
            OrdenCompra oc = SistemaCompras.getInstance().generarOrdenCompra(afe.getArticulo().getId());
            p.marcarPendiente();
            logger.info("Pedido aprobado exitosamente, se generó orden de compra con id '" + oc.getId() + "'!");
        } finally {
            p.guardar();
        }

    }
}
