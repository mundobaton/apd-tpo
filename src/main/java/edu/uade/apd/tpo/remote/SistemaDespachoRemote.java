package edu.uade.apd.tpo.remote;

import edu.uade.apd.tpo.controller.SistemaDespacho;
import edu.uade.apd.tpo.exception.BusinessException;
import edu.uade.apd.tpo.repository.SistemaDespachoRepository;
import edu.uade.apd.tpo.repository.exception.RemoteBusinessException;
import org.modelmapper.ModelMapper;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class SistemaDespachoRemote extends UnicastRemoteObject implements SistemaDespachoRepository {

    private static SistemaDespachoRemote instance;
    private ModelMapper mapper;
    private SistemaDespacho controller;

    private SistemaDespachoRemote() throws RemoteException {
        mapper = new ModelMapper();
        controller = SistemaDespacho.getInstance();
    }

    public static SistemaDespachoRemote getInstance() throws RemoteException {
        if (instance == null) {
            instance = new SistemaDespachoRemote();
        }
        return instance;
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
