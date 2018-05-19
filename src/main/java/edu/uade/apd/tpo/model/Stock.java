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
    
    public void agregarMovimientoIngreso(MotivoIngreso motivo, int cantidad) {
    	Ingreso ingreso = new Ingreso();
    	ingreso.setMotivoIngreso(motivo);
    	this.movimientos.add(ingreso);
    	this.cantidad += cantidad; 
    }
}
