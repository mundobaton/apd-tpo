package edu.uade.apd.tpo.controller;

import java.util.List;

import edu.uade.apd.tpo.dao.ArticuloDao;
import edu.uade.apd.tpo.dao.ClienteDao;
import edu.uade.apd.tpo.dao.PedidoDao;
import edu.uade.apd.tpo.model.Articulo;
import edu.uade.apd.tpo.model.Cliente;
import edu.uade.apd.tpo.model.CondicionIva;
import edu.uade.apd.tpo.model.CuentaCorriente;
import edu.uade.apd.tpo.model.Domicilio;
import edu.uade.apd.tpo.model.Envio;
import edu.uade.apd.tpo.model.EstadoPedido;
import edu.uade.apd.tpo.model.ItemPedido;
import edu.uade.apd.tpo.model.Pedido;
import edu.uade.apd.tpo.model.Stock;
import edu.uade.apd.tpo.model.Zona;

public class SistemaAdministracion {
	
	private static SistemaAdministracion instance;
	private ClienteDao clienteDao;
	private ArticuloDao articuloDao;
	private PedidoDao pedidoDao;

	private SistemaAdministracion() {
		this.clienteDao = clienteDao.getInstance();
		this.articuloDao = articuloDao.getInstance();
		this.pedidoDao = pedidoDao.getInstance();
	}

	public static SistemaAdministracion getInstance() {
	     if (instance == null) {
	            instance = new SistemaAdministracion();
	        }
	        return instance;
	}
	
	public boolean login(String email, String password) {
		Cliente cliente = buscarCliente(email);
		String pass = cliente.getPassword();
		return pass == password;
	}
	
	public Cliente buscarCliente(Long cuil){
		return clienteDao.findByCuil(cuil);
	}
	
	public Cliente buscarCliente(String email){
		return clienteDao.findByEmail(email);
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
        pedido.guardar();
        return pedido;
	}
	
	public Pedido buscarPedido(Long pedidoId){
		return pedidoDao.findById(pedidoId);
	}
	
	public Articulo buscarArticulo(Long articuloId) {
		return articuloDao.findById(articuloId);
	}
	
	public void agregarItemPedido(Long pedidoId, Long articuloId, int cant) {
		Pedido pedido = buscarPedido(pedidoId);
		if(pedido != null) {
			Articulo articulo = buscarArticulo(articuloId);
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
				SistemaCompras.getInstance().generarOrdenCompra(articulo.getId());
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

}
