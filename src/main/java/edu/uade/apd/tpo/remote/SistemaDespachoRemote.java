package edu.uade.apd.tpo.remote;

import edu.uade.apd.tpo.controller.SistemaDeposito;
import edu.uade.apd.tpo.controller.SistemaDespacho;
import edu.uade.apd.tpo.exception.BusinessException;
import edu.uade.apd.tpo.repository.SistemaDepositoRepository;
import edu.uade.apd.tpo.repository.SistemaDespachoRepository;
import edu.uade.apd.tpo.repository.exception.RemoteBusinessException;
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
    public void alistarPedido(Long pedidoId) throws RemoteException {
        try {
            controller.alistarPedido(pedidoId);
        } catch (BusinessException be) {
            throw new RemoteBusinessException(be.getMessage());
        }

    }

    @Override
    public void despacharPedido(Long pedidoId) throws RemoteException {
        try {
            controller.despacharPedido(pedidoId);
        } catch (BusinessException be) {
            throw new RemoteBusinessException(be.getMessage());
        }

    }
}
