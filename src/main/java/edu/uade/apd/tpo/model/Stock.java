package edu.uade.apd.tpo.model;

import java.util.List;

public class Stock {

    private Long id;
    private int cantidad;
    private List<Movimiento> movimientos;

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

    public List<Movimiento> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(List<Movimiento> movimientos) {
        this.movimientos = movimientos;
    }

    public void agregarMovimientoEgreso(MotivoEgreso motivo, int cantidad){
        Egreso egreso = new Egreso();
        egreso.setMotivoEgreso(motivo);
        this.movimientos.add(egreso);
        this.cantidad += cantidad;
    }

    public void agregarMovimientoEgreso(MotivoEgreso motivo, Usuario encargado, String autorizante, String destino, int cantidad){

        Egreso egreso = new Egreso();
        egreso.setMotivoEgreso(motivo);
        egreso.setEncargado(encargado);
        egreso.setAutorizante(autorizante);
        egreso.setDestino(destino);

        this.movimientos.add(egreso);
        this.cantidad += cantidad;

    }

}
