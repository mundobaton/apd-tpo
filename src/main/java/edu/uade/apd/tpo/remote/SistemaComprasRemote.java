package edu.uade.apd.tpo.remote;

import edu.uade.apd.tpo.controller.SistemaCompras;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class SistemaComprasRemote extends UnicastRemoteObject implements SistemaComprasRepository {

    private static SistemaComprasRemote instance;
    private SistemaCompras controller;

    private SistemaComprasRemote() throws RemoteException {
        this.controller = SistemaCompras.getInstance();
    }

    public static SistemaComprasRemote getInstance() throws RemoteException {
        if (instance == null) {
            instance = new SistemaComprasRemote();
        }
        return instance;
    }
    
    @Override
    public void generarOrdenCompra(Long articuloId, Long pedidoId) throws BusinessException {
		controller.generarOrdenCompra(articuloId, pedidoId);
	}

    @Override
	public OrdenCompraStub buscarOrdenCompra(Long ordenId) {
		return controller.buscarOrdenCompra(ordenId);
	}

    @Override
	public void aceptarOrdenCompra(Long ordenId) throws BusinessException {
		controller.aceptarOrdenCompra(ordenId);
	}

}

