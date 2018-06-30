package edu.uade.apd.tpo.remote;

import edu.uade.apd.tpo.controller.SistemaFacturacion;
import edu.uade.apd.tpo.exception.BusinessException;
import edu.uade.apd.tpo.repository.SistemaFacturacionRepository;
import edu.uade.apd.tpo.repository.exception.RemoteBusinessException;
import org.modelmapper.ModelMapper;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class SistemaFacturacionRemote extends UnicastRemoteObject implements SistemaFacturacionRepository {

    private static SistemaFacturacionRemote instance;
    private ModelMapper mapper;
    private SistemaFacturacion controller;

    private SistemaFacturacionRemote() throws RemoteException {
        this.mapper = new ModelMapper();
        this.controller = SistemaFacturacion.getInstance();
    }

    public static SistemaFacturacionRemote getInstance() throws RemoteException {
        if (instance == null) {
            instance = new SistemaFacturacionRemote();
        }
        return instance;
    }

    @Override
    public void facturar(Long pedidoId) throws RemoteException {
        try {
            controller.facturar(pedidoId);
        } catch (BusinessException be) {
            throw new RemoteBusinessException(be.getMessage());
        }
    }

    @Override
    public void pagarFactura(Long facturaId, Float importe) throws RemoteException {
        try {
            controller.pagarFactura(facturaId, importe);
        } catch (BusinessException be) {
            throw new RemoteBusinessException(be.getMessage());
        }
    }
}
