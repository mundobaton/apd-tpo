package edu.uade.apd.tpo.remote;

import edu.uade.apd.tpo.controller.SistemaFacturacion;
import edu.uade.apd.tpo.repository.SistemaFacturacionRepository;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class SistemaFacturacionRemote extends UnicastRemoteObject implements SistemaFacturacionRepository {

    private static SistemaFacturacionRemote instance;
    private SistemaFacturacion controller;

    private SistemaFacturacionRemote() throws RemoteException {
        this.controller = SistemaFacturacion.getInstance();
    }

    public static SistemaFacturacionRemote getInstance() throws RemoteException {
        if (instance == null) {
            instance = new SistemaFacturacionRemote();
        }
        return instance;
    }
    @Override
    public FacturaStub buscarFactura(Long facturaId) {
		return controller.buscarFactura(facturaId);
	}
	
    @Override
	public void facturar(Long pedidoId) {
		controller.facturar(pedidoId);
	}
	
    @Override
	public float procesarPago(Long facturaId, float importe, MedioPago mp, float saldo, float limiteCred) {
		return controller.procesarPago(facturaId, importe, mp, saldo, limiteCred);
	}
	
    @Override
	public List<FacturaStub> obtenerFacturasImpagas(Long cuil){
		return controller.obtenerFacturasImpagas(cuil);
	}
	
    @Override
	public float procesarPagoImporte(Long cuil, float importe, MedioPago mp, float saldo, float limiteCred) {
		return procesarPagoImporte(cuil, importe, mp, saldo, limiteCred); 
	}
}
