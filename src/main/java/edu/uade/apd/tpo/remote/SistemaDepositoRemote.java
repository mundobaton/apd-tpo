package edu.uade.apd.tpo.remote;

import edu.uade.apd.tpo.controller.SistemaDeposito;
import edu.uade.apd.tpo.model.Articulo;
import edu.uade.apd.tpo.repository.SistemaDepositoRepository;
import edu.uade.apd.tpo.repository.dto.ArticuloDTO;
import org.modelmapper.ModelMapper;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.stream.Collectors;

public class SistemaDepositoRemote extends UnicastRemoteObject implements SistemaDepositoRepository {

    private static SistemaDepositoRemote instance;
    private SistemaDeposito controller;
    private ModelMapper mapper;

    private SistemaDepositoRemote() throws RemoteException {
        this.controller = SistemaDeposito.getInstance();
        mapper = new ModelMapper();
    }

    public static SistemaDepositoRemote getInstance() throws RemoteException {
        if (instance == null) {
            instance = new SistemaDepositoRemote();
        }
        return instance;
    }

    @Override
    public List<ArticuloDTO> getArticulos() throws RemoteException {
        List<Articulo> articulos = controller.getArticulos();
        if (articulos != null && !articulos.isEmpty()) {
            return articulos.parallelStream().map(a -> mapper.map(a, ArticuloDTO.class)).collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public ArticuloDTO findArticuloById(Long articuloId) throws RemoteException {
        Articulo articulo = controller.buscarArticulo(articuloId);
        if (articulo != null) {
            return mapper.map(articulo, ArticuloDTO.class);
        }
        return null;
    }
}
