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
import edu.uade.apd.tpo.repository.SistemaAdministracionRepository;
import edu.uade.apd.tpo.repository.SistemaDepositoRepository;
import edu.uade.apd.tpo.repository.exception.RemoteBusinessException;
import edu.uade.apd.tpo.repository.stub.*;

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
    public void completarPedido(Long pedidoId) throws RemoteException {
        try {
            controller.completarPedido(pedidoId);
        } catch (BusinessException be) {
            throw new RemoteBusinessException(be.getMessage());
        }
    }
}


