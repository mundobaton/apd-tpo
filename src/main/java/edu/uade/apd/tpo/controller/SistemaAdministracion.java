package edu.uade.apd.tpo.controller;

import java.util.Iterator;
import java.util.List;

import edu.uade.apd.tpo.dao.ArticuloDao;
import edu.uade.apd.tpo.dao.ClienteDao;
import edu.uade.apd.tpo.dao.PedidoDao;
import edu.uade.apd.tpo.dao.UsuarioDao;
import edu.uade.apd.tpo.entity.ClienteEntity;
import edu.uade.apd.tpo.entity.PedidoEntity;
import edu.uade.apd.tpo.model.Articulo;
import edu.uade.apd.tpo.model.Cliente;
import edu.uade.apd.tpo.model.CondicionIva;
import edu.uade.apd.tpo.model.CuentaCorriente;
import edu.uade.apd.tpo.model.Domicilio;
import edu.uade.apd.tpo.model.Envio;
import edu.uade.apd.tpo.model.EstadoPedido;
import edu.uade.apd.tpo.model.Factura;
import edu.uade.apd.tpo.model.ItemPedido;
import edu.uade.apd.tpo.model.MedioPago;
import edu.uade.apd.tpo.model.Pedido;
import edu.uade.apd.tpo.model.Rol;
import edu.uade.apd.tpo.model.Stock;
import edu.uade.apd.tpo.model.Usuario;
import edu.uade.apd.tpo.model.Zona;
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

    public List<Pedido> obtenerPedidosParaAprobar() {
        return pedidoDao.getInstance().obtenerPedidosPreAprobadosRevision();
    }

    public Cliente buscarCliente(Long cuil) {
        return clienteDao.findByCuil(cuil);
    }

    public Cliente buscarCliente(String email) {
        return clienteDao.findByEmail(email);
    }

    public void crearUsuario(String email, String password, Rol rol) {
        Usuario u = new Usuario();
        u.setEmail(email);
        u.setPassword(password);
        u.setRol(rol);
        u.guardar();
    }

    public List<Articulo> obtenerArticulos() {
        return SistemaDeposito.getInstance().obtenerArticulos();
    }

    public void crearCliente(Long cuil, String email, String password, String nombre,
                             String telefono, String calle, Long num, String cp, String loc,
                             String prov, CondicionIva condIva, Zona zona, float saldo, float limiteCredito) {
        //TODO implementar exception
        if (buscarCliente(cuil) != null) {
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
        }
    }

    public Pedido generarPedido(Long cuil, String calle, Long num, String cp, String loc, String prov, Zona zona) {
        Cliente cliente = buscarCliente(cuil);
        //TODO implementar exception
        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
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
        pedido.guardar();
        return pedido;
    }

    public Pedido buscarPedido(Long pedidoId) {
        return pedidoDao.findById(pedidoId);
    }


    public void agregarItemPedido(Long pedidoId, Long articuloId, int cant) {
        Pedido pedido = buscarPedido(pedidoId);
        //TODO implementar excepciones pedido y articulo
        if (pedido != null) {
            Articulo articulo = SistemaDeposito.getInstance().buscarArticulo(articuloId);
            if (articulo != null) {
                pedido.agregarItem(articulo, cant);
                pedido.guardar();
            }
        }
    }

    private boolean validarCtaCteCliente(Pedido pedido) {
        return (pedido.getCliente().getCuentaCorriente().getSaldo() + pedido.getCliente().getCuentaCorriente().getLimiteCredito()) >= pedido.obtenerTotal();
    }

    public void notificarClienteEstadoPedido(Long pedidoId) {

    }

    public void cerrarPedido(Long pedidoId) {
        Pedido pedido = buscarPedido(pedidoId);
        //TODO implementar excepcion
        if (pedido != null) {
            pedido.iniciar();
            pedido.guardar();
            notificarClienteEstadoPedido(pedido.getId());

            if (validarCtaCteCliente(pedido)) {
                pedido.preAprobar();
            } else {
                pedido.revision();
            }
            pedido.guardar();
        }
    }

    public void aprobarPedido(Long pedidoId) {
        Pedido pedido = buscarPedido(pedidoId);
        //TODO implementar excepcion
        if (pedido != null) {
            pedido.aprobar();
            notificarClienteEstadoPedido(pedido.getId());
            verificarPedido(pedido);
        }
    }

    private void verificarPedido(Pedido pedido) {
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
        pedido.guardar();
    }

    public void realizarPago(Long facturaId, float importe, MedioPago mp) {
        Factura factura = SistemaFacturacion.getInstance().buscarFactura(facturaId);
        //TODO implementar excepcion
        if (factura != null) {
            Cliente cliente = factura.getPedido().getCliente();
            CuentaCorriente ctaCte = cliente.getCuentaCorriente();
            float saldo = ctaCte.getSaldo();
            float limiteCred = ctaCte.getLimiteCredito();
            float saldoRestante = SistemaFacturacion.getInstance().procesarPago(facturaId, importe, mp, saldo, limiteCred);
            ctaCte.setSaldo(saldoRestante);
            cliente.guardar();
        }
    }

    public void realizarPagoImporte(Long cuil, float importe, MedioPago mp) {
        Cliente cliente = buscarCliente(cuil);
        //TODO implemnetar exception
        if (cliente != null) {
            CuentaCorriente ctaCte = cliente.getCuentaCorriente();
            float saldo = ctaCte.getSaldo();
            float limiteCred = ctaCte.getLimiteCredito();
            float saldoRestante = SistemaFacturacion.getInstance().procesarPagoImporte(cliente.getCuil(), importe, mp, saldo, limiteCred);
            ctaCte.setSaldo(saldoRestante);
            cliente.guardar();
        }
    }

    public void rechazarPedido(Long pedidoId, String motivo) {
        Pedido p = buscarPedido(pedidoId);
        //TODO implementar excepcion
        if(p != null){
            p.rechazar(motivo);
            p.guardar();
        }
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

    public void eliminarItemPedido(Long pedidoId, Long articuloId) {
        //TODO implementar excepciones
        Articulo articulo = SistemaDeposito.getInstance().buscarArticulo(articuloId);
        Pedido pedido = buscarPedido(pedidoId);
        Iterator<ItemPedido> it = pedido.getItems().iterator();

        while (it.hasNext()) {
            ItemPedido item = it.next();
            if (item.getArticulo().getId() == articulo.getId()) {
                it.remove();
            }
        }

        pedido.guardar();
    }
    
    public List<Pedido> obtenerPedidoCompletos(){
    	return pedidoDao.getInstance().obtenerPedidosCompletos();
    }
}
