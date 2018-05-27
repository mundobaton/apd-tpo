package edu.uade.apd.tpo.remote;

import edu.uade.apd.tpo.controller.SistemaDespacho;
import edu.uade.apd.tpo.repository.SistemaDespachoRepository;
import edu.uade.apd.tpo.repository.stub.OrdenCompraStub;
import edu.uade.apd.tpo.repository.stub.PedidoStub;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.List;

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
    public List<OrdenCompraStub> obtenerOrdenesDeCompraEmitidas() {
        return null;
    }

    @Override
    public PedidoStub buscarPedido(Long pedidoId) {
        return null;
    }

    @Override
    public void despacharPedido(Long pedidoId) {
    }

    @Override
    public void alistarPedido(Long idPedido) {
    }

    @Override
    public List<PedidoStub> obtenerPedidosCompletos() {
        return null;
    }

    @Override
    public void notificarFechaDeEnvioAsignada(Date fechaEnvio) {
    }
}
