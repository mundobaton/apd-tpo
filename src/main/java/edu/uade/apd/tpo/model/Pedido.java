package edu.uade.apd.tpo.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Pedido {

    private Long id;
    private Date fechaPedido;
    private Date fechaEntrega;
    private Date fechaDespacho;
    private float precioTotal;
    private List<ItemPedido> items;
    private Cliente cliente;
    private Domicilio domicilio;
    private List<Estado> estados;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(Date fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public Date getFechaDespacho() {
        return fechaDespacho;
    }

    public void setFechaDespacho(Date fechaDespacho) {
        this.fechaDespacho = fechaDespacho;
    }

    public float getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(float precioTotal) {
        this.precioTotal = precioTotal;
    }

    public List<ItemPedido> getItems() {
        return items;
    }

    public void setItems(List<ItemPedido> items) {
        this.items = items;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Domicilio getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }

    public List<Estado> getEstados() {
        return estados;
    }

    public void setEstados(List<Estado> estados) {
        this.estados = estados;
    }

    public void addEstado(Estado estado) {
        if (this.estados == null) {
            estados = new ArrayList<>();
        }
        this.estados.add(estado);
    }

    public void addItem(ItemPedido itemPedido) {
        if (this.items == null) {
            items = new ArrayList<>();
        }
        this.items.add(itemPedido);
    }
}
