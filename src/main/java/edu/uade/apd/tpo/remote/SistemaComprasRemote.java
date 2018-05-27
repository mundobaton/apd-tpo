package edu.uade.apd.tpo.remote;

import edu.uade.apd.tpo.controller.SistemaCompras;
import edu.uade.apd.tpo.exception.BusinessException;
import edu.uade.apd.tpo.repository.SistemaComprasRepository;
import edu.uade.apd.tpo.repository.stub.OrdenCompraStub;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

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
    public void generarOrdenCompra(Long articuloId, Long pedidoId) throws RemoteException {
    }

    @Override
    public OrdenCompraStub buscarOrdenCompra(Long ordenId) throws RemoteException {
        return null;
    }

    @Override
    public void aceptarOrdenCompra(Long ordenId) throws RemoteException {
    }

    @Override
    public List<OrdenCompraStub> obtenerOrdenesDeCompraEmitidas() {
        return null;
    }

}

