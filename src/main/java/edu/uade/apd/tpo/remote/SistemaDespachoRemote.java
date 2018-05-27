package edu.uade.apd.tpo.remote;

import edu.uade.apd.tpo.controller.SistemaDespacho;
import edu.uade.apd.tpo.repository.SistemaDespachoRepository;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class SistemaDespachoRemote extends UnicastRemoteObject implements SistemaDespachoRepository {
    private static SistemaDespachoRemote instance;
    private SistemaDespacho controller;

    private SistemaDespachoRemote() throws RemoteException {
        this.controller = SistemaDespacho.getInstance();
    }

    public static SistemaDespachoRemote getInstance() throws RemoteException {
        if (instance == null) {
            instance = new SistemaDespachoRemote();
        }
        return instance;
    }
    
    @Override
    public List<OrdenCompraStub> obtenerOrdenesDeCompraEmitidas(){
		return controller.obtenerOrdenesDeCompraEmitidas();
	}
	
    @Override
	public PedidoStub buscarPedido(Long pedidoId) {
		return controller.buscarPedido(pedidoId);
	}
    
    @Override
	public void despacharPedido(Long pedidoId) {
		controller.despacharPedido(pedidoId);
	}
	
    @Override
	public void alistarPedido(Long idPedido) {
		controller.alistarPedido(idPedido);
	}
    
    @Override
	public List<PedidoStub> obtenerPedidosCompletos(){
		return controller.obtenerPedidosCompletos();
	}
}
