package edu.uade.apd.tpo.controller;

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
	
	public List<Pedido> obtenerPedidosParaAprobar(){
		return pedidoDao.getInstance().obtenerPedidosPreAprobadosRevision();
	}
	
	public Cliente buscarCliente(Long cuil){
		return clienteDao.findByCuil(cuil);
	}
	
	public Cliente buscarCliente(String email){
		return clienteDao.findByEmail(email);
	}
	
	public void crearUsuario(String email, String password, Rol rol) {
        Usuario u = new Usuario();
        u.setEmail(email);
        u.setPassword(password);
        u.setRol(rol);
        u.guardar();
    }
	
	public List<Articulo> obtenerArticulos(){
		return SistemaDeposito.getInstance().obtenerArticulos();
	}
	
	public void crearCliente(Long cuil, String email, String password, String nombre, 
								String telefono, String calle, Long num, String cp, String loc, 
								String prov, CondicionIva condIva, Zona zona, float saldo, float limiteCredito ) {
		if(buscarCliente(cuil) != null) {
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
	
	public Pedido buscarPedido(Long pedidoId){
		return pedidoDao.findById(pedidoId);
	}
	
	
	public void agregarItemPedido(Long pedidoId, Long articuloId, int cant) {
		Pedido pedido = buscarPedido(pedidoId);
		if(pedido != null) {
			Articulo articulo = SistemaDeposito.getInstance().buscarArticulo(articuloId);
			if(articulo != null){
				pedido.agregarItem(articulo, cant);
				pedido.guardar();
			}
		}
	}
	
	public boolean validarCtaCteCliente(Pedido pedido) {
		float total = pedido.obtenerTotal();
		Cliente cliente = pedido.getCliente();
		CuentaCorriente ctaCte = cliente.getCuentaCorriente();
		return ctaCte.getSaldo() + ctaCte.getLimiteCredito() >= total;
	}
	
	public void notificarClienteEstadoPedido(Long pedidoId, EstadoPedido estado, String motivo) {
		
	}
	
	public void notificarClienteEstadoPedido(Long pedidoId, EstadoPedido estado) {
		
	}
	
	public void cerrarPedido(Long pedidoId) {
		Pedido pedido = buscarPedido(pedidoId);
		if(pedido != null) {
			pedido.iniciar();
			pedido.guardar();
			notificarClienteEstadoPedido(pedidoId, EstadoPedido.INICIADO);
			if(validarCtaCteCliente(pedido)) {
				pedido.preAprobar();
				pedido.guardar();
			}else {
				pedido.revision();
				pedido.guardar();
			}
		}
	}
	
	public void aprobarPedido(Long pedidoId) {
		Pedido pedido = buscarPedido(pedidoId);
		if(pedido != null) {
			pedido.aprobar();
			notificarClienteEstadoPedido(pedidoId, EstadoPedido.APROBADO);
			verificarPedido(pedido);
		}
	}
	
	private void verificarPedido(Pedido pedido) {
		List<ItemPedido> items = pedido.getItems();
		boolean pedidoCompleto = true;
		for(ItemPedido item : items) {
			Articulo articulo = item.getArticulo();
			Stock stock = articulo.getStock();
			int cantidad = item.getCantidad();
			if(stock.calcular() >= cantidad) {
				stock.reservar(cantidad);
			}else {
				SistemaCompras.getInstance().generarOrdenCompra(articulo.getId(), pedido.getId());
				pedidoCompleto = false;
			}
		}
		if(pedidoCompleto) {
			pedido.verificado();
		}else {
			pedido.marcarPendiente();
			notificarClienteEstadoPedido(pedido.getId(), EstadoPedido.PENDIENTE, "Falta de stock");
		}
		pedido.guardar();
	}

	public void realizarPago(Long facturaId, float importe, MedioPago mp) {
		Factura factura = SistemaFacturacion.getInstance().buscarFactura(facturaId);
		Cliente cliente = factura.getPedido().getCliente();
		CuentaCorriente ctaCte = cliente.getCuentaCorriente();
		float saldo = ctaCte.getSaldo();
		float limiteCred = ctaCte.getLimiteCredito();
		float saldoRestante = SistemaFacturacion.getInstance().procesarPago(facturaId, importe, mp, saldo, limiteCred);
		ctaCte.setSaldo(saldoRestante);
		cliente.guardar();
	}
	
	public void realizarPagoImporte(Long cuil, float importe, MedioPago mp) {
		Cliente cliente = buscarCliente(cuil);
		CuentaCorriente ctaCte = cliente.getCuentaCorriente();
		float saldo = ctaCte.getSaldo();
		float limiteCred = ctaCte.getLimiteCredito();
		float saldoRestante = SistemaFacturacion.getInstance().procesarPagoImporte(cuil, importe, mp, saldo, limiteCred);
		ctaCte.setSaldo(saldoRestante);
		cliente.guardar();
	}
	
	public void rechazarPedido(Long pedidoId, String motivo) {
        Pedido p = this.buscarPedido(pedidoId);
        p.rechazar(motivo);
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
    	Articulo articulo = SistemaDeposito.getInstance().buscarArticulo(articuloId);
    	Pedido pedido = buscarPedido(pedidoId);
    	List<ItemPedido> items = pedido.getItems();
    	for(ItemPedido item : items) {
    		if(item.getArticulo().getId() == articulo.getId()) {
    			item.setCantidad(0);
    		}
    	}
    	pedido.guardar();
    }
    
    public List<Pedido> obtenerPedidoCompletos(){
    	return pedidoDao.getInstance().obtenerPedidosCompletos();
    }
}
