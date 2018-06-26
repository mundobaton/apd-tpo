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

    public void crearCliente(String nombreUsuario, String password, String calle, int numero, String localidad, String provincia, String codPostal, float saldo, float credito) throws BusinessException {
        Cliente cli = this.buscarCliente(nombreUsuario);
        if (cli != null) {
            throw new BusinessException("El cliente '" + nombreUsuario + "' ya existe");
        }
        cli = new Cliente(nombreUsuario, password, calle, numero, localidad, provincia, codPostal, 500f, 300f);
        cli.guardar();
        logger.info("Cliente '" + nombreUsuario + "' creado exitosamente");
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

    public void crearPedido(String nombreUsuario, String calle, int numero, String localidad, String provincia, String codPostal) throws BusinessException {
        Cliente cli = this.buscarCliente(nombreUsuario);
        if (cli == null) {
            throw new BusinessException("El cliente '" + nombreUsuario + "' no existe");
        }
        Pedido pedido = new Pedido(calle, numero, localidad, provincia, codPostal, cli);
        pedido = pedido.guardar();
        logger.info("Se ha generado el pedido '" + pedido.getId() + "' exitosamente");
    }

    public void agregarItemPedido(Long pedidoId, Long articuloId, int cantidad) throws BusinessException {
        Pedido pedido = this.buscarPedido(pedidoId);
        if (pedido == null) {
            throw new BusinessException("El pedido '" + pedidoId + "' no existe");
        }
        if (pedido.getEstado() != EstadoPedido.GENERADO) {
            throw new BusinessException("El pedido debe encontrarse en estado GENERADO, actual '" + pedido.getEstado().toString() + "'");
        }
        Articulo art = SistemaDeposito.getInstance().buscarArticulo(articuloId);
        if (art == null) {
            throw new BusinessException("El articulo '" + articuloId + "' no existe");
        }
        pedido.agregarItem(art, cantidad);
        pedido.guardar();
    }

    public void finalizarCargaItems(Long pedidoId) throws BusinessException {
        Pedido pedido = this.buscarPedido(pedidoId);
        if (pedido == null) {
            throw new BusinessException("El pedido '" + pedidoId + "' no existe");
        }

        if (pedido.getEstado() != EstadoPedido.GENERADO) {
            throw new BusinessException("El pedido debe encontrarse en estado GENERADO, actual '" + pedido.getEstado().toString() + "'");
        }

        //Validar cuenta corriente del cliente
        Cliente cliente = pedido.getCliente();
        if (cliente.getCuentaCorriente().tieneSaldoDisponible(pedido.getPrecioBruto())) {
            pedido.setEstado(EstadoPedido.PREAPROBADO);
        } else {
            pedido.setEstado(EstadoPedido.SALDO_INSUFICIENTE);
        }
        pedido.guardar();
    }

    public void aprobarPedido(Long pedidoId) throws BusinessException {
        Pedido pedido = this.buscarPedido(pedidoId);
        if (pedido == null) {
            throw new BusinessException("El pedido '" + pedidoId + "' no existe");
        }
        aprobarPedido(pedido);
    }

    public void aprobarPedido(Long pedidoId, String mensaje) throws BusinessException {
        Pedido pedido = this.buscarPedido(pedidoId);
        if (pedido == null) {
            throw new BusinessException("El pedido '" + pedidoId + "' no existe");
        }
        Cliente cliente = pedido.getCliente();
        cliente.getCuentaCorriente().agregarNota(mensaje, pedido);
        aprobarPedido(pedido);
    }

    private void aprobarPedido(Pedido pedido) throws BusinessException {
        pedido.cerrar();
        pedido.guardar();

        //Intento procesar el pedido, si no existe suficiente stock genera ordenes de compra y quedara en estado pendiente.
        //Caso contrario queda en estado completo
        pedido.procesar();
    }

    private Cliente buscarCliente(String nombreUsuario) {
        return clienteDao.findByNombreUsuario(nombreUsuario);
    }

    private Usuario buscarUsuario(String legajo) {
        return usuarioDao.findByLegajo(legajo);
    }

    public Pedido buscarPedido(Long pedidoId) {
        return pedidoDao.findById(pedidoId);
    }
}
