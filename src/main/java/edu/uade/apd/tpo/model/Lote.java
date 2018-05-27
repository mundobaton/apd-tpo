package edu.uade.apd.tpo.model;

import edu.uade.apd.tpo.dao.LoteDao;
import edu.uade.apd.tpo.entity.ArticuloEntity;
import edu.uade.apd.tpo.entity.LoteEntity;
import edu.uade.apd.tpo.entity.PosicionEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Lote {
    private Long id;
    private String codigo;
    private Date fechaVto;
    private Date fechaElaboracion;
    private List<Posicion> posiciones;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Date getFechaVto() {
        return fechaVto;
    }

    public void setFechaVto(Date fechaVto) {
        this.fechaVto = fechaVto;
    }

    public Date getFechaElaboracion() {
        return fechaElaboracion;
    }

    public void setFechaElaboracion(Date fechaElaboracion) {
        this.fechaElaboracion = fechaElaboracion;
    }

    public List<Posicion> getPosiciones() {
        return posiciones;
    }

    public void setPosiciones(List<Posicion> posiciones) {
        this.posiciones = posiciones;
    }

    public void guardar() {
        LoteDao.getInstance().save(this.toEntity());
    }

    public static Lote fromEntity(LoteEntity entity) {
        Lote l = null;
        if (entity != null) {
            l = new Lote();
            l.setId(entity.getId());
            l.setCodigo(entity.getCodigo());
            l.setFechaElaboracion(entity.getFechaElaboracion());
            l.setFechaVto(entity.getFechaVto());
            if (entity.getPosiciones() != null) {
                l.setPosiciones(new ArrayList<>());
                for (PosicionEntity pe : entity.getPosiciones()) {
                    l.getPosiciones().add(Posicion.fromEntity(pe));
                }
            }
        }
        return l;
    }

    public LoteEntity toEntity() {
        LoteEntity entity = new LoteEntity();
        entity.setId(id);
        entity.setCodigo(codigo);
        entity.setFechaElaboracion(fechaElaboracion);
        entity.setFechaVto(fechaVto);

        if (posiciones != null) {
            entity.setPosiciones(new ArrayList<>());
            for (Posicion p : posiciones) {
                entity.getPosiciones().add(p.toEntity());
            }
        }
        return entity;
    }

}
