package edu.uade.apd.tpo.model;

public class itemPosicion {

    private Long id;
    private int cantidad;
    private Posicion posicion;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Posicion getPosicion() {
        return posicion;
    }

    public void setPosicion(Posicion posicion) {
        this.posicion = posicion;
    }

}
