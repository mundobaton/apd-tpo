package edu.uade.apd.tpo.remote;

import edu.uade.apd.tpo.controller.SistemaAdministracion;
import edu.uade.apd.tpo.controller.SistemaCompras;
import edu.uade.apd.tpo.controller.SistemaDeposito;
import edu.uade.apd.tpo.exception.BusinessException;
import edu.uade.apd.tpo.model.Articulo;
import edu.uade.apd.tpo.model.EstadoPosicion;
import edu.uade.apd.tpo.model.ItemLote;
import edu.uade.apd.tpo.model.ItemPedido;
import edu.uade.apd.tpo.model.ItemPosicion;
import edu.uade.apd.tpo.model.Lote;
import edu.uade.apd.tpo.model.MotivoEgreso;
import edu.uade.apd.tpo.model.MotivoIngreso;
import edu.uade.apd.tpo.model.OrdenCompra;
import edu.uade.apd.tpo.model.Pedido;
import edu.uade.apd.tpo.model.Posicion;
import edu.uade.apd.tpo.model.Stock;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SistemaDepositoRemote extends UnicastRemoteObject implements SistemaDepositoRepository {

    private static SistemaDepositoRemote instance;
    private SistemaDeposito controller;

    private SistemaDepositoRemote() throws RemoteException {
        this.controller = SistemaDeposito.getInstance();
    }

    public static SistemaDepositoRemote getInstance() throws RemoteException {
        if (instance == null) {
            instance = new SistemaDepositoRemote();
        }
        return instance;
    }
    
   
    @Override
    public void completarPedido(Long pedidoId){
		return controller.completarPedido(pedidoId);
	}
    
    @Override
    public void ingresarCompra(Long ordenId, List<ItemLote> items) {
		return controller.ingresarCompra(ordenId, items);
	}
    
    @Override
    public void almacenar(Articulo articulo, List<ItemLote> itemLotes, int cantidad) {
		return controller.almacenar(articulo, itemLotes, cantidad);
    }
    
    @Override
    public void  aceptarOrdenCompra(Long ordenId) {
		return controller.aceptarOrdenCompra(ordenId);
    } 
    
    
    @Override
    public PosicionStub buscarPosicionPorUbicacion(String ubicacion) {
		return controller.buscarPosicionPorUbicacion(ubicacion);
    } 

    @Override
    public int liberarPosicion(String codUbicacion, int cantidad){
		return controller.liberarPosicion(codUbicacion, cantidad);
    }

    @Override
    public List<ArticuloStub> obtenerArticulos(){
		return controller.obtenerArticulos();
    }
	
    @Override
    public LoteStub  crearLote(String codigo, Date fechaVen, Date fechaElab, Long idArticulo) {
    	return controller.crearLote(codigo, fechaVen, fechaElab, idArticulo);
    }

    @Override
	public List<PedidoStub> obtenerPedidosACompletar(){
		return controller.obtenerPedidosACompletar();
	}
 }


