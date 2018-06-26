package edu.uade.apd.tpo.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ItemLote {

    private Long id;
    private String codigo;
    private Date fechaVencimiento;
    private List<Ubicacion> ubicaciones;

    public ItemLote(String codigo, Date fechaVencimiento) {
        this.codigo = codigo;
        this.fechaVencimiento = fechaVencimiento;
        this.ubicaciones = new ArrayList<>();
    }

    public ItemLote() {

    }

    public String getCodigo() {
        return codigo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public List<Ubicacion> getUbicaciones() {
        return ubicaciones;
    }

    public void setUbicaciones(List<Ubicacion> ubicaciones) {
        this.ubicaciones = ubicaciones;
    }

    public void vaciar() {
        this.ubicaciones = null;
    }
}
