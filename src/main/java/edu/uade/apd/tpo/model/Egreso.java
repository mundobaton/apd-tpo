package edu.uade.apd.tpo.model;

import edu.uade.apd.tpo.entity.EgresoEntity;

public class Egreso extends Movimiento {

    private MotivoEgreso motivoEgreso;
    private Usuario encargado;
    private String autorizante;
    private String destino;

    public MotivoEgreso getMotivoEgreso() {
        return motivoEgreso;
    }

    public void setMotivoEgreso(MotivoEgreso motivoEgreso) {
        this.motivoEgreso = motivoEgreso;
    }

    public Usuario getEncargado() {
        return encargado;
    }

    public void setEncargado(Usuario encargado) {
        this.encargado = encargado;
    }

    public String getAutorizante() {
        return autorizante;
    }

    public void setAutorizante(String autorizante) {
        this.autorizante = autorizante;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public static Egreso fromEntity(EgresoEntity entity) {
        Egreso egreso = null;
        if(entity != null) {
            egreso = new Egreso();
            egreso.setId(entity.getId());
            egreso.setMotivoEgreso(entity.getMotivoEgreso());
            egreso.setEncargado(Usuario.fromEntity(entity.getEncargado()));
            egreso.setAutorizante(entity.getAutorizante());
            egreso.setDestino(entity.getDestino());
        }
        return egreso;
    }

    public EgresoEntity toEntity() {
        EgresoEntity egreso = new EgresoEntity();
        egreso.setId(id);
        egreso.setMotivoEgreso(motivoEgreso);
        egreso.setEncargado(encargado.toEntity());
        egreso.setAutorizante(autorizante);
        egreso.setDestino(destino);
        return egreso;
    }
}