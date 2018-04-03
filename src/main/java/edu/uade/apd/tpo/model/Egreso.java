package edu.uade.apd.tpo.model;

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
}
