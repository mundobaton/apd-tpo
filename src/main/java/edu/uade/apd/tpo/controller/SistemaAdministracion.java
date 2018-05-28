package edu.uade.apd.tpo.controller;

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
import edu.uade.apd.tpo.repository.exception.UserNotFoundException;

public class SistemaAdministracion {

    private static SistemaAdministracion instance;
    private ClienteDao clienteDao;
    private PedidoDao pedidoDao;
    private UsuarioDao usuarioDao;

    private SistemaAdministracion() {
        this.clienteDao = ClienteDao.getInstance();
        this.pedidoDao = PedidoDao.getInstance();
        this.usuarioDao = UsuarioDao.getInstance();
    }

    public static SistemaAdministracion getInstance() {
        if (instance == null) {
            instance = new SistemaAdministracion();
        }
        return instance;
    }

    public List<Usuario> getUsuarios() {
        return usuarioDao.getInstance().findAll().parallelStream().map(ue -> Usuario.fromEntity(ue)).collect(Collectors.toList());
    }

    public void actualizarUsuario(Usuario u) {
        u.guardar();
    }

    public List<Cliente> getClientes() {
        return clienteDao.getInstance().findAll().parallelStream().map(ce -> Cliente.fromEntity(ce)).collect(Collectors.toList());
    }

    public List<Pedido> obtenerPedidosParaAprobar() {
        return pedidoDao.getInstance().obtenerPedidosPreAprobadosRevision()
                .parallelStream().map(pe -> Pedido.fromEntity(pe)).collect(Collectors.toList());
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
        Usuario u = this.buscarUsuario(email);
        if (u == null) {
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
        Factura factura = SistemaFacturacion.getInstance().buscarFactura(facturaId);
        if (factura != null) {
            //TODO Buscar cliente por factura
            Cliente cliente = new Cliente();
            CuentaCorriente ctaCte = cliente.getCuentaCorriente();
            float saldo = ctaCte.getSaldo();
            float limiteCred = ctaCte.getLimiteCredito();
            float saldoRestante = SistemaFacturacion.getInstance().procesarPago(facturaId, importe, mp, saldo,
                    limiteCred);
            ctaCte.setSaldo(saldoRestante);
            cliente.guardar();
        } else {
            throw new BusinessException("No existe factura");

        }
    }

    public void realizarPagoImporte(Long cuil, float importe, MedioPago mp) throws BusinessException {
        Cliente cliente = buscarCliente(cuil);
        if (cliente != null) {
            CuentaCorriente ctaCte = cliente.getCuentaCorriente();
            float saldo = ctaCte.getSaldo();
            float limiteCred = ctaCte.getLimiteCredito();
            float saldoRestante = SistemaFacturacion.getInstance().procesarPagoImporte(cliente.getCuil(), importe, mp,
                    saldo, limiteCred);
            ctaCte.setSaldo(saldoRestante);
            cliente.guardar();
        } else {
            throw new BusinessException("No existe cliente");
        }
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

    public Usuario login(String email, String password) throws UserNotFoundException {
        Usuario u = Usuario.fromEntity(UsuarioDao.getInstance().findByEmail(email));
        if (u == null || !validarPassword(u.getPassword(), password)) {
            throw new UserNotFoundException("El usuario no existe o el password es incorrecto");
        }
        return u;
    }

    private boolean validarPassword(String userPassword, String inputPassword) {
        return userPassword.equals(inputPassword);
    }

    public void eliminarItemPedido(Long pedidoId, Long articuloId) throws BusinessException {

        Articulo articulo = SistemaDeposito.getInstance().buscarArticulo(articuloId);
        Pedido pedido = buscarPedido(pedidoId);
        if (articulo != null) {
            if (pedido != null) {
                Iterator<ItemPedido> it = pedido.getItems().iterator();
                while (it.hasNext()) {
                    ItemPedido item = it.next();
                    if (item.getArticulo().getId() == articulo.getId()) {
                        it.remove();
                    }
                }
            } else {
                throw new BusinessException("No existe pedido");
            }
        } else {
            throw new BusinessException("No existe articulo");
        }
        pedido.guardar();
    }

    public List<Pedido> obtenerPedidoCompletos() {
        return pedidoDao.getInstance().obtenerPedidosCompletos()
                .parallelStream().map(pe -> Pedido.fromEntity(pe)).collect(Collectors.toList());
    }

    public Cliente obtenerClientePorPedido(Long pedidoId){
        List<Cliente> clientes = clienteDao.findAll().parallelStream().map(ce -> Cliente.fromEntity(ce)).collect(Collectors.toList());
        Cliente result = null;
        for(Cliente c : clientes){
            if(c.getPedidos() != null && !c.getPedidos().isEmpty()){
                for(Pedido p : c.getPedidos()){
                    if(p.getId() == pedidoId){
                        result = c;
                    }
                }
            }
        }
        return result;
    }

    public List<Pedido> obtenerPedidosListos() {
        return pedidoDao.getInstance().obtenerPedidosListos()
                .parallelStream().map(pe -> Pedido.fromEntity(pe)).collect(Collectors.toList());
    }

    public List<Pedido> obtenerPedidosACompletar() {
        return pedidoDao.getInstance().obtenerPedidosVerificados()
                .parallelStream().map(pe -> Pedido.fromEntity(pe)).collect(Collectors.toList());
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
