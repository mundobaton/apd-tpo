package edu.uade.apd.tpo.model;

import edu.uade.apd.tpo.dao.EstadoDao;
import edu.uade.apd.tpo.entity.EstadoEntity;

import java.util.Date;

public class Estado {

    private Long id;
    private EstadoPedido estado;
    private Date fecha;
    private String motivo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public static Estado fromEntity(EstadoEntity entity) {
        Estado estado = null;
        if (entity != null) {
            estado = new Estado();
            estado.setId(entity.getId());
            estado.setEstado(entity.getEstado());
            estado.setFecha(entity.getFecha());
            estado.setMotivo(entity.getMotivo());
        }

        return estado;
    }

    public EstadoEntity toEntity() {
        EstadoEntity ee = new EstadoEntity();
        ee.setId(id);
        ee.setEstado(estado);
        ee.setFecha(fecha);
        ee.setMotivo(motivo);

        return ee;
    }

    public void guardar() {
        EstadoDao.getInstance().save(this.toEntity());
    }

}
