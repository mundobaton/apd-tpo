package edu.uade.apd.tpo.model;

public class Posicion {

    private Long id;
    private final static int CAPACIDAD = 21;
    private String codigoUbicacion;
    private EstadoPosicion estado;
    private Lote lote;
    private int cantidad;
    private char calle;
    private int bloque;
    private int estanteria;
    private int estante;
    private int numero;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigoUbicacion() {
        return codigoUbicacion;
    }

    public void setCodigoUbicacion(String codigoUbicacion) {
        this.codigoUbicacion = codigoUbicacion;
    }

    public EstadoPosicion getEstado() {
        return estado;
    }

    public void setEstado(EstadoPosicion estado) {
        this.estado = estado;
    }

    public Lote getLote() {
        return lote;
    }

    public void setLote(Lote lote) {
        this.lote = lote;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public char getCalle() {
        return calle;
    }

    public void setCalle(char calle) {
        this.calle = calle;
    }

    public int getBloque() {
        return bloque;
    }

    public void setBloque(int bloque) {
        this.bloque = bloque;
    }

    public int getEstanteria() {
        return estanteria;
    }

    public void setEstanteria(int estanteria) {
        this.estanteria = estanteria;
    }

    public int getEstante() {
        return estante;
    }

    public void setEstante(int estante) {
        this.estante = estante;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
}
