package edu.uade.apd.tpo.model;

import edu.uade.apd.tpo.entity.UsuarioEntity;

public class Egreso extends Movimiento {

    private MotivoEgreso motivoEgreso;
    private UsuarioEntity encargado;
    private String autorizante;
    private String destino;

    public MotivoEgreso getMotivoEgreso() {
        return motivoEgreso;
    }

    public void setMotivoEgreso(MotivoEgreso motivoEgreso) {
        this.motivoEgreso = motivoEgreso;
    }

    public UsuarioEntity getEncargado() {
        return encargado;
    }

    public void setEncargado(UsuarioEntity encargado) {
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
}
