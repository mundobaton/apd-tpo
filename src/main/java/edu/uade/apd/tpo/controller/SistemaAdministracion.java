package edu.uade.apd.tpo.controller;

import edu.uade.apd.tpo.dao.ClienteDao;
import edu.uade.apd.tpo.dao.PedidoDao;
import edu.uade.apd.tpo.dao.UsuarioDao;
import edu.uade.apd.tpo.exception.BusinessException;
import edu.uade.apd.tpo.model.Articulo;
import edu.uade.apd.tpo.model.Cliente;
import edu.uade.apd.tpo.model.EstadoPedido;
import edu.uade.apd.tpo.model.Pedido;
import edu.uade.apd.tpo.model.Rol;
import edu.uade.apd.tpo.model.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class SistemaAdministracion {

    private static SistemaAdministracion instance;
    private ClienteDao clienteDao;
    private UsuarioDao usuarioDao;
    private PedidoDao pedidoDao;
    private static final Logger logger = LoggerFactory.getLogger(SistemaAdministracion.class);


    private SistemaAdministracion() {
        this.clienteDao = ClienteDao.getInstance();
        this.usuarioDao = UsuarioDao.getInstance();
        this.pedidoDao = PedidoDao.getInstance();
    }

    public static SistemaAdministracion getInstance() {
        if (instance == null) {
            instance = new SistemaAdministracion();
        }
        return instance;
    }

    public void crearCliente(String email, String nombre, Long cuit, String password, String calle, int numero, String localidad, String provincia, String codPostal, float saldo, float credito) throws BusinessException {
        Cliente cli = this.buscarCliente(email);
        if (cli != null) {
            throw new BusinessException("El cliente con email:'" + email + "' ya existe");
        }
        cli = new Cliente(email, nombre, cuit, password, calle, numero, localidad, provincia, codPostal, saldo, credito);
        cli.guardar();
        logger.info("Cliente '" + email + "' creado exitosamente");
    }

    public void crearUsuario(String legajo, String password, Rol rol) throws BusinessException {
        Usuario u = this.buscarUsuario(legajo);
        if (u != null) {
            throw new BusinessException("El usuario '" + legajo + "' ya existe");
        }
        u = new Usuario(legajo, password, rol);
        u.guardar();
        logger.info("Usuario '" + legajo + "' creado exitosamente");
    }

    public Long crearPedido(Long clienteId, String calle, int numero, String localidad, String provincia, String codPostal) throws BusinessException {
        Cliente cli = this.buscarCliente(clienteId);
        if (cli == null) {
            throw new BusinessException("El cliente '" + clienteId + "' no existe");
        }
        Pedido pedido = new Pedido(calle, numero, localidad, provincia, codPostal, cli);
        pedido = pedido.guardar();
        logger.info("Se ha generado el pedido '" + pedido.getId() + "' exitosamente");
        return pedido.getId();
    }

    public void agregarItemPedido(Long pedidoId, Long articuloId, int cantidad) throws BusinessException {
        Pedido pedido = this.buscarPedido(pedidoId);
        if (pedido == null) {
            throw new BusinessException("El pedido '" + pedidoId + "' no existe");
        }

        Articulo art = SistemaDeposito.getInstance().buscarArticulo(articuloId);
        if (art == null) {
            throw new BusinessException("El articulo '" + articuloId + "' no existe");
        }
        pedido.agregarItem(art, cantidad);
    }

    public void finalizarCargaItems(Long pedidoId) throws BusinessException {
        Pedido pedido = this.buscarPedido(pedidoId);
        if (pedido == null) {
            throw new BusinessException("El pedido '" + pedidoId + "' no existe");
        }
        pedido.finalizarCargaItems();
    }

    public void aprobarPedido(Long pedidoId) throws BusinessException {
        Pedido pedido = this.buscarPedido(pedidoId);
        if (pedido == null) {
            throw new BusinessException("El pedido '" + pedidoId + "' no existe");
        }
        pedido.aprobar();
    }

    public void aprobarPedido(Long pedidoId, String mensaje) throws BusinessException {
        Pedido pedido = this.buscarPedido(pedidoId);
        if (pedido == null) {
            throw new BusinessException("El pedido '" + pedidoId + "' no existe");
        }
        Cliente cliente = pedido.getCliente();
        cliente.getCuentaCorriente().agregarNota(mensaje, pedido);
        pedido.aprobar();
    }

    public void rechazarPedido(Long pedidoId, String mensaje) throws BusinessException {
        Pedido pedido = this.buscarPedido(pedidoId);
        if (pedido == null) {
            throw new BusinessException("El pedido '" + pedidoId + "' no existe");
        }
        Cliente cliente = pedido.getCliente();
        cliente.getCuentaCorriente().agregarNota(mensaje, pedido);
        pedido.rechazar();
    }

    private Cliente buscarCliente(String email) {
        return clienteDao.findByEmail(email);
    }

    private Usuario buscarUsuario(String legajo) {
        return usuarioDao.findByLegajo(legajo);
    }

    public Pedido buscarPedido(Long pedidoId) {
        return pedidoDao.findById(pedidoId);
    }

    public Cliente loginCliente(String email, String password) throws BusinessException {
        Cliente cliente = this.buscarCliente(email);
        if (cliente == null) {
            throw new BusinessException("La dirección de email '" + email + "' o contraseña es incorrecta");
        }
        if (!password.equals(cliente.getPassword())) {
            throw new BusinessException("La dirección de email '" + email + "' o contraseña es incorrecta");
        }
        return cliente;
    }

    public Usuario loginUsuario(String legajo, String password) throws BusinessException {
        Usuario usuario = this.buscarUsuario(legajo);
        if (usuario == null) {
            throw new BusinessException("El usuario '" + legajo + "' o contraseña es incorrecta");
        }
        if (!password.equals(usuario.getPassword())) {
            throw new BusinessException("El usuario '" + legajo + "' o contraseña es incorrecta");
        }
        return usuario;
    }

    public Cliente buscarClienteById(Long clienteId) throws BusinessException {
        Cliente cliente = buscarCliente(clienteId);
        if (cliente == null) {
            throw new BusinessException("El cliente con id '" + clienteId + "' no existe");
        }
        return cliente;
    }

    private Cliente buscarCliente(Long clienteId) {
        return clienteDao.findById(clienteId);
    }

    public List<Pedido> findPedidosByEstado(EstadoPedido ep) {
        return pedidoDao.findByEstado(ep);
    }
    
    public Pedido findPedidoById(Long pedidoId) throws BusinessException{
        Pedido pedido = pedidoDao.findById(pedidoId);
        if (pedido == null) {
            throw new BusinessException("El pedido con id '" + pedidoId + "' no existe");
        }
        return pedido;
    }

}
