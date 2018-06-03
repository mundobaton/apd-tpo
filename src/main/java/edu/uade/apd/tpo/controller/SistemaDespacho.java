package edu.uade.apd.tpo.controller;

<<<<<<< HEAD
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import edu.uade.apd.tpo.exception.BusinessException;
import edu.uade.apd.tpo.model.Cliente;
import edu.uade.apd.tpo.model.OrdenCompra;
import edu.uade.apd.tpo.model.Pedido;
import edu.uade.apd.tpo.model.Transportista;

public class SistemaDespacho {

    private static SistemaDespacho instance;

    private SistemaDespacho() {
    }

    public static SistemaDespacho getInstance() {
        if (instance == null) {
            instance = new SistemaDespacho();
        }
        return instance;
    }

    public List<OrdenCompra> obtenerOrdenesDeCompraEmitidas() {
        return SistemaCompras.getInstance().obtenerOrdenesDeCompraEmitidas();
    }

    public Pedido buscarPedido(Long pedidoId) {
        return SistemaAdministracion.getInstance().buscarPedido(pedidoId);
    }

    public void despacharPedido(Long pedidoId) throws BusinessException {

        Cliente cli = SistemaAdministracion.getInstance().obtenerClientePorPedido(pedidoId);
        if(cli == null) throw new BusinessException("No se ha encontrado un cliente con el pedido "+pedidoId);

        Pedido pedido = cli.obtenerPedido(pedidoId);
        if(pedido == null) throw new BusinessException("No se ha encontrado el pedido "+pedidoId);

        Date fechaEnvio = new Date();
        Calendar f = Calendar.getInstance();
        f.setTime(fechaEnvio);
        f.add(Calendar.DATE, 4);
        fechaEnvio = f.getTime();

        pedido.setFechaEntrega(fechaEnvio);
        pedido.setFechaDespacho(new Date());

        notificarFechaDeEnvioAsignada(fechaEnvio);

        pedido.enviado();
        cli.guardar();
    }

    public void alistarPedido(Long idPedido) throws BusinessException {
        Cliente cli = SistemaAdministracion.getInstance().obtenerClientePorPedido(idPedido);
        if(cli == null) throw new BusinessException("No existe un cliente con el pedido "+idPedido);

        Pedido pedido = cli.obtenerPedido(idPedido);
        if(pedido == null) throw new BusinessException("El cliente no cuenta con el pedido "+idPedido);

        pedido.alistar(seleccionarTransportista());
        cli.guardar();

        SistemaFacturacion.getInstance().facturar(pedido.getId());
    }

    private Transportista seleccionarTransportista() {
        Random random = new Random();
        int index = random.nextInt(2);
        return Transportista.values()[index];
    }

    public List<Pedido> obtenerPedidosCompletos() {
        return SistemaAdministracion.getInstance().obtenerPedidoCompletos();
    }

    public void notificarFechaDeEnvioAsignada(Date fechaEnvio) {

    }
=======
import java.util.List;

import edu.uade.apd.tpo.dao.impl.PedidoDao;
import edu.uade.apd.tpo.model.*;

public class SistemaDespacho {

	private static SistemaDespacho instance;
	private PedidoDao pedidoDao;

	private SistemaDespacho() {
		this.pedidoDao = PedidoDao.getInstance();
	}

	public static SistemaDespacho getInstance() {
	     if (instance == null) {
	            instance = new SistemaDespacho();
	        }
	        return instance;
	}
	
	public void despacharPedido(Long pedidoId) {

		Pedido p = buscarPedido(pedidoId);

		for(ItemPedido item : p.getItems()){

			item.getArticulo().getStock().agregarMovimientoEgreso(MotivoEgreso.VENTA, item.getCantidad());

			for(ItemLote lote : item.getLotes()){
				for(Posicion posicion : lote.getLote().getPosiciones()){
					//SistemaDeposito.getInstance().liberarPosicion(posicion.getCodigoUbicacion(), item.getCantidad());
				}
			}
			item.getArticulo().guardar();
		}
		SistemaFacturacion.getInstance().facturar(pedidoId);
	}
	
	public List<Pedido> obtenerPedidosCompletos(){
		return pedidoDao.obtenerPedidosCompletos();
	}
	
	public Pedido buscarPedido(Long pedidoId) {
		return pedidoDao.findById(pedidoId);
	}
	
	
	
>>>>>>> develop
}
