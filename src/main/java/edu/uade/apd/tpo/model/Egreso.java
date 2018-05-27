package edu.uade.apd.tpo.model;

import edu.uade.apd.tpo.dao.EgresoDao;
import edu.uade.apd.tpo.entity.EgresoEntity;
import edu.uade.apd.tpo.entity.IngresoEntity;

public class Egreso extends Movimiento {
    private MotivoEgreso motivo;
    private Usuario encargado;
    private String autorizante;
    private String destino;


    public MotivoEgreso getMotivo() {
        return motivo;
    }

    public void setMotivo(MotivoEgreso motivo) {
        this.motivo = motivo;
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

    public int cantidad() {
        return this.cantidad;
    }

    public static Egreso fromEntity(EgresoEntity entity) {
        Egreso e = null;
        if (entity != null) {
            e = new Egreso();
            e.setId(entity.getId());
            e.setFecha(entity.getFecha());
            e.setCantidad(entity.getCantidad());
            e.setAutorizante(entity.getAutorizante());
            e.setDestino(entity.getDestino());
            e.setMotivo(entity.getMotivo());
            e.setEncargado(Usuario.fromEntity(entity.getEncargado()));
        }
        return e;
    }

    public EgresoEntity toEntity() {
        EgresoEntity entity = new EgresoEntity();
        entity.setId(id);
        entity.setFecha(fecha);
        entity.setCantidad(cantidad);
        entity.setAutorizante(autorizante);
        entity.setDestino(destino);
        entity.setMotivo(motivo);
        entity.setEncargado(encargado != null ? encargado.toEntity() : null);
        return entity;
    }

    public void guardar() {
        EgresoDao.getInstance().save(this.toEntity());
    }
}
