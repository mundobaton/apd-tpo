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
import edu.uade.apd.tpo.repository.SistemaDepositoRepository;
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
    public ArticuloStub buscarArticulo(Long articuloId) {
        return null;
    }

    @Override
    public void completarPedido(Long pedidoId) {
    }

    @Override
    public void ingresarCompra(Long ordenId, List<ItemLoteStub> items) throws RemoteException {
    }

    @Override
    public void almacenar(ArticuloStub articulo, List<ItemLoteStub> itemLotes, int cantidad) throws RemoteException {
    }

    @Override
    public void aceptarOrdenCompra(Long ordenId) throws RemoteException {
    }

    @Override
    public PosicionStub buscarPosicionPorUbicacion(String ubicacion) {
        return null;
    }

    @Override
    public int liberarPosicion(String codUbicacion, int cantidad) {
        return 0;
    }

    @Override
    public List<ArticuloStub> obtenerArticulos() {
        return null;
    }

    @Override
    public LoteStub crearLote(String codigo, Date fechaVen, Date fechaElab, Long idArticulo) {
        return null;
    }

    @Override
    public List<PedidoStub> obtenerPedidosACompletar() {
        return null;
    }


}


