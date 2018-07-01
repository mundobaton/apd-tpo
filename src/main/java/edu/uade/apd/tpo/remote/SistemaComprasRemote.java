package edu.uade.apd.tpo.remote;

import edu.uade.apd.tpo.controller.SistemaCompras;
import edu.uade.apd.tpo.exception.BusinessException;
import edu.uade.apd.tpo.model.OrdenCompra;
import edu.uade.apd.tpo.repository.SistemaComprasRepository;
import edu.uade.apd.tpo.repository.dto.OrdenCompraDTO;
import edu.uade.apd.tpo.repository.exception.RemoteBusinessException;
import org.modelmapper.ModelMapper;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SistemaComprasRemote extends UnicastRemoteObject implements SistemaComprasRepository {

    private static SistemaComprasRemote instance;
    private ModelMapper mapper;
    private SistemaCompras controller;

    private SistemaComprasRemote() throws RemoteException {
        this.mapper = new ModelMapper();
        this.controller = SistemaCompras.getInstance();
    }

    public static SistemaComprasRemote getInstance() throws RemoteException {
        if (instance == null) {
            instance = new SistemaComprasRemote();
        }
        return instance;
    }

    @Override
    public void procesarOrdenCompra(Long ordenCompraId) throws RemoteException {
        try {
            controller.procesarOrdenCompra(ordenCompraId);
        } catch (BusinessException be) {
            throw new RemoteBusinessException(be.getMessage());
        }
    }

    @Override
    public void procesarOrdenesCompra() throws RemoteException {
        try {
            controller.procesarOrdenesCompra();
        } catch (BusinessException be) {
            throw new RemoteBusinessException(be.getMessage());
        }
    }

    @Override
    public List<OrdenCompraDTO> getOrdenesCompra() throws RemoteException {
        List<OrdenCompra> ocs =  controller.obtenerOrdenesCompra();
        if(ocs!=null) {
        	List<OrdenCompraDTO> ocdto = new ArrayList<>();
        	for(OrdenCompra oc : ocs) {
        		ocdto.add(mapper.map(oc, OrdenCompraDTO.class));
        	}
        	return ocdto;
        }
        return null;
    }

    @Override
    public OrdenCompraDTO findById(Long ordenCompraId) throws RemoteException {
        OrdenCompra oc = controller.buscarOrdenCompra(ordenCompraId);
        if (oc != null) {
            return mapper.map(oc, OrdenCompraDTO.class);
        }
        return null;
    }
}
