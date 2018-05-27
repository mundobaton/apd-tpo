package edu.uade.apd.tpo.remote;

import edu.uade.apd.tpo.controller.SistemaFacturacion;
import edu.uade.apd.tpo.model.MedioPago;
import edu.uade.apd.tpo.repository.SistemaFacturacionRepository;
import edu.uade.apd.tpo.repository.stub.FacturaStub;
import edu.uade.apd.tpo.repository.stub.MedioPagoStub;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

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
        return null;
    }

    @Override
    public void facturar(Long pedidoId) {
    }

    @Override
    public float procesarPago(Long facturaId, float importe, MedioPagoStub mp, float saldo, float limiteCred) {
        return 0;
    }

    @Override
    public List<FacturaStub> obtenerFacturasImpagas(Long cuil) {
        return null;
    }

    @Override
    public float procesarPagoImporte(Long cuil, float importe, MedioPagoStub mp, float saldo, float limiteCred) {
        return 0;
    }
}
