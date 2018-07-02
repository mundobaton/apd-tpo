package edu.uade.apd.tpo.model;

import edu.uade.apd.tpo.controller.SistemaDeposito;
import edu.uade.apd.tpo.dao.ReposicionDao;
import edu.uade.apd.tpo.exception.BusinessException;

public class Reposicion {

    private Long id;
    private OrdenCompra ordenCompra;
    private int cantidad;
    private char estado;

    public Reposicion(OrdenCompra ordenCompra) {
        this.ordenCompra = ordenCompra;
        cantidad = ordenCompra.getItem().getArticulo().getCantCompra();
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

    public OrdenCompra getOrdenCompra() {
        return ordenCompra;
    }

    public void setOrdenCompra(OrdenCompra ordenCompra) {
        this.ordenCompra = ordenCompra;
    }

    public void procesar(int cantidad) throws BusinessException {
        if (this.estado != 'P') {
            throw new BusinessException("La reposicion debe estar en estado 'P', actual: " + Character.toString(estado));
        }
        this.estado = 'C';
        this.cantidad = cantidad;
        SistemaDeposito.getInstance().almacenar(this);
        guardar();
        ordenCompra.getPedido().procesar();
    }
}
