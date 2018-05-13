package edu.uade.apd.tpo.controller;

import edu.uade.apd.tpo.dao.impl.ArticuloDao;
import edu.uade.apd.tpo.dao.impl.ClienteDao;
import edu.uade.apd.tpo.dao.impl.PedidoDao;
import edu.uade.apd.tpo.dao.impl.UsuarioDao;
import edu.uade.apd.tpo.entity.UsuarioEntity;
import edu.uade.apd.tpo.model.Articulo;
import edu.uade.apd.tpo.model.Cliente;
import edu.uade.apd.tpo.model.CondIva;
import edu.uade.apd.tpo.model.CuentaCorriente;
import edu.uade.apd.tpo.model.Domicilio;
import edu.uade.apd.tpo.model.Estado;
import edu.uade.apd.tpo.model.EstadoPedido;
import edu.uade.apd.tpo.model.ItemPedido;
import edu.uade.apd.tpo.model.MedioPago;
import edu.uade.apd.tpo.model.Pedido;
import edu.uade.apd.tpo.model.Rol;
import edu.uade.apd.tpo.model.Usuario;
import edu.uade.apd.tpo.model.ZonaEnvio;
import edu.uade.apd.tpo.repository.exception.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;

public class SistemaAdministracion {

    private static SistemaAdministracion instance;
    private UsuarioDao usuarioDao;
    private ClienteDao clienteDao;
    private PedidoDao pedidoDao;
    private ArticuloDao articuloDao;
    private static final Logger logger = LoggerFactory.getLogger(SistemaAdministracion.class);

    private SistemaAdministracion() {
        this.usuarioDao = UsuarioDao.getInstance();
        this.clienteDao = ClienteDao.getInstance();
        this.pedidoDao = PedidoDao.getInstance();
        this.articuloDao = ArticuloDao.getInstance();
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

    public void crearUsuario(String email, String password, Rol rol) {
        logger.debug("Creando usuario...");
        Usuario u = new Usuario();
        u.setEmail(email);
        u.setPassword(password);
        u.setRol(rol);
        u.guardar();
        logger.debug("Usuario creado exitosamente...");
    }

    public void actualizarUsuario(Usuario u) {
        logger.debug("Modificando usuario...");
        u.guardar();
        logger.debug("Usuario modificado exitosamente...");
    }

    public void crearCliente(String email, String password, String nombre, long cuil, String telefono, CondIva condIva, String calle, int numero, String codPostal, String localidad, String provincia, ZonaEnvio zona, float saldo, float limiteCredito) {
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
    }

    /**
     * Debe devolver la cabecera del pedido, no se le agregan items aun
     *
     * @param email
     * @param calle
     * @param num
     * @param codPostal
     * @param localidad
     * @param prov
     * @param zona
     */
    public Long generarPedido(String email, String calle, int num, String codPostal, String localidad, String prov, ZonaEnvio zona) {
        //TODO Validar parametros!
        Cliente cli = clienteDao.findByEmail(email);
        Domicilio dom = new Domicilio(calle, num, codPostal, localidad, prov, zona);
        Pedido pedido = new Pedido();
        pedido.setCliente(cli);
        pedido.setDomicilio(dom);
        pedido.setFechaPedido(new Date());
        Estado estado = new Estado();
        estado.setMotivo("Creacion del pedido");
        estado.setEstado(EstadoPedido.INICIADO);
        estado.setFecha(new Date());
        pedido.addEstado(estado);
        pedido.guardar();
        return pedido.getId();
    }

    /**
     * Agrega items al pedido
     *
     * @param pedidoId
     * @param articuloId
     * @param cant
     */
    public void agregarItemPedido(Long pedidoId, Long articuloId, int cant) {
        //TODO Validar parametros!
        Pedido p = pedidoDao.findById(pedidoId);
        Articulo articulo = articuloDao.findById(articuloId);
        ItemPedido item = new ItemPedido();
        item.setArticulo(articulo);
        item.setCantidad(cant);
        p.addItem(item);
        p.guardar();
    }

    public void finalizarCargaItemsPedido(Long pedidoId) {
        //TODO Validar parametros!
        Pedido p = pedidoDao.findById(pedidoId);
        Estado e = new Estado();
        e.setFecha(new Date());
        e.setEstado(EstadoPedido.PENDIENTE);
        e.setMotivo("Fin carga de items al pedido");
        p.addEstado(e);
        p.guardar();
    }

    public Usuario buscarUsuario(String email) {
        //TODO Validar parametros!
        return UsuarioDao.getInstance().findByEmail(email);
    }

    public void aprobarPedido(Long pedidoId) {
        //Todo validar parametros e integridad del pedido, checkear estado
        Pedido p = this.buscarPedido(pedidoId);
        p.aprobar();

        //TODO Aca se debería verificar si existe stock para completar el pedido,
        //TODO si se puede cumplir hacerlo y sino realizar la OC correspondiente
    }

    public void rechazarPedido(Long pedidoId, String motivo) {
        Pedido p = this.buscarPedido(pedidoId);
        p.rechazar(motivo);
    }

    public List<Articulo> obtenerArticulos() {
        return articuloDao.findAll();
    }

    public Usuario login(String email, String password) throws UserNotFoundException {
        Usuario u = UsuarioDao.getInstance().findByEmail(email);
        if (!validarPassword(u.getPassword(), password)) {
            throw new UserNotFoundException("El usuario no existe o el password es incorrecto");
        }
        return u;
    }

    private boolean validarPassword(String userPassword, String inputPassword) {
        return userPassword.equals(inputPassword);
    }

    private Pedido buscarPedido(Long pedidoId) {
        return PedidoDao.getInstance().findById(pedidoId);
    }

    public void cerrarPedido(Long pedidoId) {
        Pedido p = this.buscarPedido(pedidoId);
        p.cerrar();
    }

    public void procesarPago(String email, float importe, MedioPago mp) {
        Cliente cli = clienteDao.findByEmail(email);
        SistemaFacturacion.getInstance().procesarPago(email, importe, mp, cli.getCuentaCorriente().getLimiteCredito());
    }
}
