package edu.uade.apd.tpo.controller;

<<<<<<< HEAD
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import edu.uade.apd.tpo.dao.ClienteDao;
import edu.uade.apd.tpo.dao.PedidoDao;
import edu.uade.apd.tpo.dao.UsuarioDao;
import edu.uade.apd.tpo.entity.ClienteEntity;
import edu.uade.apd.tpo.entity.PedidoEntity;
import edu.uade.apd.tpo.entity.UsuarioEntity;
import edu.uade.apd.tpo.exception.BusinessException;
import edu.uade.apd.tpo.model.*;
=======
import edu.uade.apd.tpo.dao.impl.ArticuloDao;
import edu.uade.apd.tpo.dao.impl.ClienteDao;
import edu.uade.apd.tpo.dao.impl.PedidoDao;
import edu.uade.apd.tpo.dao.impl.UsuarioDao;
import edu.uade.apd.tpo.entity.UsuarioEntity;
import edu.uade.apd.tpo.exception.ArticulosFaltantesException;
import edu.uade.apd.tpo.exception.BusinessException;
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

import java.util.Collection;
import java.util.Date;
import java.util.List;
>>>>>>> develop

public class SistemaAdministracion {

    private static SistemaAdministracion instance;
<<<<<<< HEAD
    private ClienteDao clienteDao;
    private PedidoDao pedidoDao;
    private UsuarioDao usuarioDao;

    private SistemaAdministracion() {
        this.clienteDao = ClienteDao.getInstance();
        this.pedidoDao = PedidoDao.getInstance();
        this.usuarioDao = UsuarioDao.getInstance();
=======
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
>>>>>>> develop
    }

    public static SistemaAdministracion getInstance() {
        if (instance == null) {
            instance = new SistemaAdministracion();
        }
        return instance;
    }

    public List<Usuario> getUsuarios() {
<<<<<<< HEAD
        return usuarioDao.getInstance().findAll().parallelStream().map(ue -> Usuario.fromEntity(ue)).collect(Collectors.toList());
    }

    public void actualizarUsuario(Usuario u) {
        u.guardar();
    }

    public List<Cliente> getClientes() {
        return clienteDao.getInstance().findAll().parallelStream().map(ce -> Cliente.fromEntity(ce)).collect(Collectors.toList());
    }

    public Cliente buscarCliente(Long cuil) {
        ClienteEntity entity = clienteDao.findByCuil(cuil);
        return entity == null ? null : Cliente.fromEntity(entity);
    }

    public Cliente buscarCliente(String email) throws BusinessException {
        ClienteEntity entity = clienteDao.findByEmail(email);
        if (entity != null) {
            return Cliente.fromEntity(entity);
        }
        throw new BusinessException("El cliente con email: '" + email + "' no existe");
    }

    public Usuario buscarUsuario(String email) throws BusinessException {
        UsuarioEntity entity = usuarioDao.findByEmail(email);
        if (entity != null) {
            return Usuario.fromEntity(entity);
        }
        throw new BusinessException("El usuario con email: '" + email + "' no existe");
    }

    public void crearUsuario(String email, String password, Rol rol) throws BusinessException {
        Usuario u;
        try {
            u = this.buscarUsuario(email);
            if (u != null) {
                throw new BusinessException("El usuario con email '" + email + "' ya existe");
            }
        } catch (BusinessException be) {
            u = new Usuario();
            u.setEmail(email);
            u.setPassword(password);
            u.setRol(rol);
            u.guardar();
        }
    }

    public List<Articulo> obtenerArticulos() {
        return SistemaDeposito.getInstance().obtenerArticulos();
    }

    public void crearCliente(Long cuil, String email, String password, String nombre, String telefono, String calle,
                             Long num, String cp, String loc, String prov, CondicionIva condIva, Zona zona, float saldo,
                             float limiteCredito) throws BusinessException {
        if (buscarCliente(cuil) == null) {
            Cliente cliente = new Cliente();
            cliente.setEmail(email);
            cliente.setPassword(password);
            cliente.setNombre(nombre);
            cliente.setCuil(cuil);
            cliente.setTelefono(telefono);
            cliente.setCondIva(condIva);
            Domicilio domicilio = new Domicilio();
            domicilio.setCalle(calle);
            domicilio.setNumero(num);
            domicilio.setCodPostal(cp);
            domicilio.setLocalidad(loc);
            domicilio.setProvincia(prov);
            domicilio.setZona(zona);
            cliente.setDomicilio(domicilio);
            CuentaCorriente cuentaCorriente = new CuentaCorriente();
            cuentaCorriente.setSaldo(saldo);
            cuentaCorriente.setLimiteCredito(limiteCredito);
            cliente.setCuentaCorriente(cuentaCorriente);
            cliente.setRol(Rol.CLIENTE);
            cliente.guardar();
        } else {
            throw new BusinessException("El cliente con cuil '" + cuil + "' ya existe");
        }
    }

    public Pedido generarPedido(Long cuil, String calle, Long num, String cp, String loc, String prov, Zona zona) throws BusinessException {
        Cliente cliente = buscarCliente(cuil);
        if (cliente != null) {
            Pedido pedido = new Pedido();
            pedido.setFechaPedido(new Date());
            Domicilio domicilio = new Domicilio();
            domicilio.setCalle(calle);
            domicilio.setNumero(num);
            domicilio.setCodPostal(cp);
            domicilio.setLocalidad(loc);
            domicilio.setProvincia(prov);
            domicilio.setZona(zona);
            Envio envio = new Envio();
            envio.setDomicilio(domicilio);
            pedido.setEnvio(envio);
            if (cliente.getPedidos() == null) {
                cliente.setPedidos(new ArrayList<>());
            }
            cliente.getPedidos().add(pedido);
            cliente = cliente.guardar();
            return cliente.getPedidos().get(cliente.getPedidos().size() - 1);
        } else {
            throw new BusinessException("No existe el cliente con cuil: " + cuil);
        }

    }

    public Pedido buscarPedido(Long pedidoId) {
        PedidoEntity entity = pedidoDao.findById(pedidoId);
        if (entity != null) {
            return Pedido.fromEntity(entity);
        }
        return null;
    }

    public void agregarItemPedido(Long pedidoId, Long cuil, Long articuloId, int cant) throws BusinessException {
        Cliente cliente = buscarCliente(cuil);
        if (cliente == null) {
            throw new BusinessException("El cliente con cuil'" + cuil + "' no existe");
        }
        Pedido pedido = cliente.obtenerPedido(pedidoId);
        if (pedido != null) {
            Articulo articulo = SistemaDeposito.getInstance().buscarArticulo(articuloId);
            if (articulo != null) {
                pedido.agregarItem(articulo, cant);
                cliente.guardar();
            } else {
                throw new BusinessException("No existe articulo");
            }
        } else {
            throw new BusinessException("No existe pedido");
        }
    }

    private boolean validarCtaCteCliente(Pedido pedido, Cliente cliente) {
        return (cliente.getCuentaCorriente().getSaldo()
                + cliente.getCuentaCorriente().getLimiteCredito()) >= pedido.obtenerTotal();

    }

    public void notificarClienteEstadoPedido(Long pedidoId) throws BusinessException {
        //TODO
    }

    public void cerrarPedido(Long pedidoId, Long cuil) throws BusinessException {
        Cliente cliente = buscarCliente(cuil);
        if (cliente == null) {
            throw new BusinessException("El cliente con cuil '" + cuil + "' no existe");
        }
        Pedido pedido = cliente.obtenerPedido(pedidoId);
        if (pedido != null) {
            pedido.iniciar();

            if (validarCtaCteCliente(pedido, cliente)) {
                pedido.preAprobar();
            } else {
                pedido.revision();
            }
            cliente.guardar();
            notificarClienteEstadoPedido(pedido.getId());

        } else {
            throw new BusinessException("El pedido '" + pedidoId + "' no existe o no pertenece al usuario");
        }

    }

    public void aprobarPedido(Long pedidoId, Long cuil, String motivo) throws BusinessException {
        Cliente cliente = buscarCliente(cuil);

        if (cliente == null) {
            throw new BusinessException("El cliente con cuil '" + cuil + "' no existe");
        }
        Pedido pedido = cliente.obtenerPedido(pedidoId);

        if (pedido != null) {
            pedido.aprobar(motivo);
            notificarClienteEstadoPedido(pedido.getId());
            verificarPedido(pedido);
        } else {
            throw new BusinessException("No existe el pedido '" + pedidoId + "' o no pertenece al usuario");
        }
        cliente.guardar();
    }

    private void verificarPedido(Pedido pedido) throws BusinessException {
        List<ItemPedido> items = pedido.getItems();
        boolean pedidoCompleto = true;
        for (ItemPedido item : items) {
            if (item.getArticulo().getStock().calcular() >= item.getCantidad()) {
                item.getArticulo().getStock().reservar(item.getCantidad());
            } else {
                SistemaCompras.getInstance().generarOrdenCompra(item.getArticulo().getId(), pedido.getId());
                pedidoCompleto = false;
            }
        }
        if (pedidoCompleto) {
            pedido.verificado();
        } else {
            pedido.marcarPendiente();
            notificarClienteEstadoPedido(pedido.getId());
        }
    }

    public void realizarPago(Long facturaId, float importe, MedioPago mp) throws BusinessException {
        SistemaFacturacion.getInstance().procesarPago(facturaId, importe, mp);
    }

    public void realizarPagoImporte(Long cuil, float importe, MedioPago mp) throws BusinessException {
        SistemaFacturacion.getInstance().procesarPagoImporte(cuil, importe, mp);
    }

    public void rechazarPedido(Long pedidoId, String motivo) throws BusinessException {
        Pedido p = buscarPedido(pedidoId);
        if (p != null) {
            p.rechazar(motivo);
            p.guardar();
        } else {
            throw new BusinessException("No existe pedido");
        }
    }

    public Usuario login(String email, String password) throws BusinessException {
        Usuario u = Usuario.fromEntity(UsuarioDao.getInstance().findByEmail(email));
        if (u == null || !validarPassword(u.getPassword(), password)) {
            throw new BusinessException("El usuario no existe o el password es incorrecto");
=======
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
        pedido.iniciar();
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
        if (p.getEstado() == EstadoPedido.INICIADO) {
            Articulo articulo = articuloDao.findById(articuloId);
            p.agregarItem(articulo, cant);
            p.guardar();
        } else {
            //TODO Arrojar business exception
        }
    }

    public void finalizarCargaItemsPedido(Long pedidoId) {
        //TODO Validar parametros!
        Pedido p = pedidoDao.findById(pedidoId);
        if(p.getEstado() == EstadoPedido.INICIADO) {
            p.cerrar();
        }
        p.guardar();
    }

    public Usuario buscarUsuario(String email) {
        //TODO Validar parametros!
        return UsuarioDao.getInstance().findByEmail(email);
    }

    public void aprobarPedido(Long pedidoId) {
        //Todo validar parametros e integridad del pedido, checkear estado
        Pedido p = this.buscarPedido(pedidoId);
        try {
            p.aprobar();
        } catch (ArticulosFaltantesException afe) {
            SistemaCompras.getInstance().generarOrdenCompra(afe.getArticulo().getId());
            p.marcarPendiente();
        }

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
>>>>>>> develop
        }
        return u;
    }

    private boolean validarPassword(String userPassword, String inputPassword) {
        return userPassword.equals(inputPassword);
    }

<<<<<<< HEAD
    public void eliminarItemPedido(Long pedidoId, Long articuloId) throws BusinessException {

        //TODO revisar persistencia ya que setea null el pedido_id pero no elimina la row
        Cliente cli = this.obtenerClientePorPedido(pedidoId);
        if (cli == null) throw new BusinessException("Cliente no encontrado.");

        Pedido p = cli.obtenerPedido(pedidoId);
        if (p == null) throw new BusinessException("No existe pedido asociado al cliente.");

        Iterator<ItemPedido> it = p.getItems().iterator();
        while (it.hasNext()) {
            ItemPedido item = it.next();
            if (item.getArticulo().getId() == articuloId) {
                it.remove();
            }
        }

        cli.guardar();
    }

    public List<Pedido> obtenerPedidoPorEstado(EstadoPedido estadoPedido) {
        List<Pedido> pedidos = pedidoDao.getInstance().findAll()
                .parallelStream().map(pe -> Pedido.fromEntity(pe)).collect(Collectors.toList());

        Iterator<Pedido> i = pedidos.iterator();
        while (i.hasNext()) {
            Pedido p = i.next();
            if (p.getEstados() != null) {
                if (p.getEstados().isEmpty()) {
                    i.remove();
                } else {
                    int ultimoEstado = p.getEstados().size() - 1;
                    if (p.getEstados().get(ultimoEstado).getEstado() != estadoPedido) {
                        i.remove();
                    }
                }
            }
        }
        return pedidos;
    }

    public List<Pedido> obtenerPedidoCompletos() {
        return obtenerPedidoPorEstado(EstadoPedido.COMPLETO);
    }

    public Cliente obtenerClientePorPedido(Long pedidoId) {
        List<Cliente> clientes = clienteDao.findAll().parallelStream().map(ce -> Cliente.fromEntity(ce)).collect(Collectors.toList());
        Cliente result = null;
        for (Cliente c : clientes) {
            if (c.getPedidos() != null && !c.getPedidos().isEmpty()) {
                for (Pedido p : c.getPedidos()) {
                    if (p.getId() == pedidoId) {
                        result = c;
                    }
                }
            }
        }
        return result;
    }

    public Cliente obtenerClientePorFactura(Long facturaId) {
        List<Cliente> clientes = clienteDao.findAll().parallelStream().map(ce -> Cliente.fromEntity(ce)).collect(Collectors.toList());
        Cliente result = null;
        for (Cliente c : clientes) {
            if (c.getPedidos() != null && !c.getPedidos().isEmpty()) {
                for (Pedido p : c.getPedidos()) {
                    if (p.getFactura() != null && p.getFactura().getId() == facturaId) {
                        result = c;
                    }
                }
            }
        }
        return result;
    }

    public List<Pedido> obtenerPedidosParaAprobar(){
        List<Pedido> preaprobados = obtenerPedidoPorEstado(EstadoPedido.PREAPROBADO);
        List<Pedido> en_revision = obtenerPedidoPorEstado(EstadoPedido.EN_REVISION);
        List<Pedido> pedidos = new ArrayList<>();
        pedidos.addAll(preaprobados);
        pedidos.addAll(en_revision);
        return pedidos;
    }

    public List<Pedido> obtenerPedidosListos() {
        return obtenerPedidoPorEstado(EstadoPedido.LISTO);
    }

    public List<Pedido> obtenerPedidosACompletar() {
        return obtenerPedidoPorEstado(EstadoPedido.VERIFICADO);
    }

    public List<Pedido> obtenerPedidosPendientes() {
        return obtenerPedidoPorEstado(EstadoPedido.PENDIENTE);
    }

    public void procesarPedidosPendientesCompraIngresada() throws BusinessException {
        List<Pedido> pedidos = obtenerPedidosPendientes();
        if (pedidos == null) throw new BusinessException("No hay pedidos pendientes.");

        for (Pedido p : pedidos) {
            verificarPedido(p);
            p.guardar();
        }
    }

    public Articulo crearArticulo(String codBarras, String descripcion, String presentacion, String unidad, int cantCompra, int volumen, float precio) {

        Articulo art = new Articulo();
        art.setCodBarras(codBarras);
        art.setDescripcion(descripcion);
        art.setPresentacion(presentacion);
        art.setUnidad(unidad);
        art.setCantCompra(cantCompra);
        art.setVolumen(volumen);
        art.setPrecio(precio);

        Stock stock = new Stock();
        if (stock.getMovimientos() == null) {
            List<Movimiento> moves = new ArrayList<>();
            stock.setMovimientos(moves);
        }
        stock.agregarMovimientoIngreso(MotivoIngreso.COMPRA, cantCompra);
        List<Lote> lotes = new ArrayList<>();

        art.setStock(stock);
        art.setLotes(lotes);
        art.guardar();

        return art;
    }
}
=======
    private Pedido buscarPedido(Long pedidoId) {
        return PedidoDao.getInstance().findById(pedidoId);
    }

    public void cerrarPedido(Long pedidoId) {
        Pedido p = this.buscarPedido(pedidoId);
        p.cerrar();
    }

    public void procesarPago(String email, float importe, MedioPago mp) {
        Cliente cli = clienteDao.findByEmail(email);
        float saldo = SistemaFacturacion.getInstance().procesarPago(email, importe, mp, cli.getCuentaCorriente().getSaldo() ,cli.getCuentaCorriente().getLimiteCredito());
        //actualizamos el saldo de la cuenta
        cli.getCuentaCorriente().setSaldo(saldo);
        cli.guardar();
    }
    
    public void procesarPago(String email, float importe, MedioPago mp, Long facturaId) throws BusinessException {
        Cliente cli = clienteDao.findByEmail(email);
        float saldo = SistemaFacturacion.getInstance().procesarPago(facturaId, importe, mp, cli.getCuentaCorriente().getSaldo() ,cli.getCuentaCorriente().getLimiteCredito());
        //actualizamos el saldo de la cuenta
        cli.getCuentaCorriente().setSaldo(saldo);
        cli.guardar();
    }

	public List<Pedido> getPedidosPendientes() {
	    return pedidoDao.findAllPending();
	}
}
>>>>>>> develop
