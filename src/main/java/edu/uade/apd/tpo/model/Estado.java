package edu.uade.apd.tpo.model;

import edu.uade.apd.tpo.entity.EstadoEntity;

import java.util.Date;

public class Estado {

    private Integer id;
    private Date fecha;
    private EstadoPedido estado;
    private String motivo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public static Estado fromEntity(EstadoEntity entity) {
        Estado estado = null;
        if (entity != null) {
            estado = new Estado();
            estado.setId(entity.getId());
            estado.setFecha(entity.getFecha());
            estado.setEstado(entity.getEstadoPedido());
            estado.setMotivo(entity.getMotivo());
        }
        return estado;
    }

    public EstadoEntity toEntity() {
        EstadoEntity entity = new EstadoEntity();
        entity.setId(id);
        entity.setFecha(fecha);
        entity.setEstadoPedido(estado);
        entity.setMotivo(motivo);
        return entity;
    }
}
