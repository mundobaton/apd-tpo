package edu.uade.apd.tpo.exception;

import edu.uade.apd.tpo.model.Articulo;

public class ArticulosFaltantesException extends Exception {

    private Articulo articulo;
    private int cantidad;

    public ArticulosFaltantesException(Articulo articulo, int cantidad) {
        this.articulo = articulo;
        this.cantidad = cantidad;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public int getCantidad() {
        return cantidad;
    }
}
