package edu.uade.apd.tpo.model;

import edu.uade.apd.tpo.controller.SistemaAdministracion;
import edu.uade.apd.tpo.controller.SistemaCompras;
import edu.uade.apd.tpo.controller.SistemaDeposito;
import edu.uade.apd.tpo.dao.OrdenCompraDao;
import edu.uade.apd.tpo.exception.BusinessException;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class OrdenCompra {

    private Long id;
    private ItemPedido item;
    private Pedido pedido;
    private char estado;
    private Date fechaCreacion;
    private String proveedor;

    public OrdenCompra(ItemPedido item, Pedido pedido) {
        this.item = item;
        this.pedido = pedido;
        this.estado = 'P';
        this.fechaCreacion = new Date();
        this.proveedor = SistemaCompras.getInstance().getProveedor();
    }

    public OrdenCompra() {

    }

    public void procesar() throws BusinessException {
        if (estado != 'P') {
            throw new BusinessException("La orden de compra debe estar en estado PENDIENTE(P)");
        }
        SistemaDeposito.getInstance().almacenar(item);
        this.estado = 'C';

        for (Iterator<ItemPedido> it = pedido.getItems().iterator(); it.hasNext();) {
            ItemPedido ip = it.next();
            if (ip.getId().longValue() == item.getId().longValue()) {
                it.remove();
            }
        }
        pedido.getItems().add(item);
        pedido.guardar();
        guardar();
        pedido.procesar();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ItemPedido getItem() {
        return item;
    }

    public void setItem(ItemPedido item) {
        this.item = item;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }

    public OrdenCompra guardar() {
        return OrdenCompraDao.getInstance().save(this);
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

}
