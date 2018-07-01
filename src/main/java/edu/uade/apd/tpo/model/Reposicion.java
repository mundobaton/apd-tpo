package edu.uade.apd.tpo.model;

import edu.uade.apd.tpo.controller.SistemaDeposito;
import edu.uade.apd.tpo.dao.ReposicionDao;
import edu.uade.apd.tpo.exception.BusinessException;

public class Reposicion {

    private Long id;
    private ItemPedido itemPedido;
    private Pedido pedido;
    private int cantidad;
    private char estado;

    public Reposicion(ItemPedido itemPedido, Pedido pedido) {
        this.itemPedido = itemPedido;
        this.pedido = pedido;
        cantidad = itemPedido.getArticulo().getCantCompra();
        this.estado = 'P';
    }

    public Reposicion() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ItemPedido getItemPedido() {
        return itemPedido;
    }

    public void setItemPedido(ItemPedido itemPedido) {
        this.itemPedido = itemPedido;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }

    public void guardar() {
        ReposicionDao.getInstance().save(this);
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public void procesar(int cantidad) throws BusinessException {
        if (this.estado != 'P') {
            throw new BusinessException("La reposicion debe estar en estado 'P', actual: " + Character.toString(estado));
        }
        this.estado = 'C';
        this.cantidad = cantidad;
        SistemaDeposito.getInstance().almacenar(this);
        pedido.guardar();
        pedido.procesar();
        guardar();
    }
}
