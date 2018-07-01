package edu.uade.apd.tpo.remote;

import edu.uade.apd.tpo.controller.SistemaDespacho;
import edu.uade.apd.tpo.exception.BusinessException;
import edu.uade.apd.tpo.model.Pedido;
import edu.uade.apd.tpo.repository.SistemaDespachoRepository;
import edu.uade.apd.tpo.repository.dto.PedidoDTO;
import edu.uade.apd.tpo.repository.exception.RemoteBusinessException;
import org.modelmapper.ModelMapper;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<PedidoDTO> obtenerPedidosCompletos() throws RemoteException {
        try {
            List<Pedido> pedidos = controller.obtenerPedidosCompletos();
            if (pedidos != null && !pedidos.isEmpty()) {
                List<PedidoDTO> result = new ArrayList<>();
                for (Pedido p : pedidos) {
                    result.add(mapper.map(p, PedidoDTO.class));
                }
                return result;
            }
            return null;
        } catch (Exception be) {
            throw new RemoteBusinessException(be.getMessage());
        }

    }
}
