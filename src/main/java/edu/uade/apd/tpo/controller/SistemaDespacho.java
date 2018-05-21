package edu.uade.apd.tpo.controller;

import edu.uade.apd.tpo.dao.PedidoDao;
import edu.uade.apd.tpo.entity.PedidoEntity;
import edu.uade.apd.tpo.exception.BusinessException;
import edu.uade.apd.tpo.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SistemaDespacho {

    private static SistemaDespacho instance;
    private static final Logger logger = LoggerFactory.getLogger(SistemaCompras.class);
    private PedidoDao pedidoDao;

    private SistemaDespacho() {
        this.pedidoDao = PedidoDao.getInstance();
    }

    public static SistemaDespacho getInstance() {
        if (instance == null) {
            instance = new SistemaDespacho();
        }
        return instance;
    }

    public void despacharPedido(Integer pedidoId) throws BusinessException{
        Pedido p = this.buscarPedido(pedidoId);
        if(p == null){
            throw new BusinessException("El pedido no existe.");
        }

        for(ItemPedido item : p.getItems()){
            item.getArticulo().getStock().agregarMovimientoEgreso(MotivoEgreso.VENTA, item.getCantidad());

            for(ItemLote lote : item.getLotes()){
//TODO persistir las posiciones donde se almacenan los lotes
            }

        }

    }

    public Pedido buscarPedido(Integer pedidoId){
        PedidoEntity entity = pedidoDao.findById(pedidoId);
        return entity == null ? null : Pedido.fromEntity(entity);
    }

}
