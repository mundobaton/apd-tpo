package edu.uade.apd.tpo.remote;

import edu.uade.apd.tpo.controller.SistemaFacturacion;
import edu.uade.apd.tpo.exception.BusinessException;
import edu.uade.apd.tpo.model.Pedido;
import edu.uade.apd.tpo.repository.SistemaFacturacionRepository;
import edu.uade.apd.tpo.repository.dto.PedidoDTO;
import edu.uade.apd.tpo.repository.exception.RemoteBusinessException;
import org.modelmapper.ModelMapper;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

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
    public void pagarFactura(Long facturaId, Float importe, Long clienteId) throws RemoteException {
        try {
            controller.pagarFactura(facturaId, importe, clienteId);
        } catch (BusinessException be) {
            throw new RemoteBusinessException(be.getMessage());
        }
    }

    @Override
    public List<PedidoDTO> obtenerPedidosFacturar() throws RemoteException {
        List<Pedido> pedidos = controller.obtenerPedidosFacturar();
        if (pedidos != null && !pedidos.isEmpty()) {
            List<PedidoDTO> result = new ArrayList<>();
            for (Pedido p : pedidos) {
                result.add(mapper.map(p, PedidoDTO.class));
            }
            return result;
        }
        return null;
    }

    @Override
    public void pagarImporte(Float importe, Long clienteId) throws RemoteException {
        try {
            controller.pagarImporte(importe, clienteId);
        } catch (BusinessException be) {
            throw new RemoteBusinessException(be.getMessage());
        }
    }
}
