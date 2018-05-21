package edu.uade.apd.tpo.model;

import edu.uade.apd.tpo.entity.LoteEntity;
import edu.uade.apd.tpo.entity.PosicionEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Lote {

    private Integer id;
    private String codigo;
    private Date fechaVto;
    private Date fechaElaboracion;
    private Articulo articulo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public static Lote fromEntity(LoteEntity entity) {
        Lote lote = null;
        if (entity != null) {
            lote = new Lote();
            lote.setId(entity.getId());
            lote.setCodigo(entity.getCodigo());
            lote.setFechaVto(entity.getFechaVto());
            lote.setFechaElaboracion(entity.getFechaElaboracion());
            lote.setArticulo(Articulo.fromEntity(entity.getArticulo()));
        }

        return lote;
    }

    public LoteEntity toEntity() {
        LoteEntity entity = new LoteEntity();
        entity.setId(id);
        entity.setCodigo(codigo);
        entity.setFechaVto(fechaVto);
        entity.setFechaElaboracion(fechaElaboracion);
        entity.setArticulo(articulo != null ? articulo.toEntity() : null);

        return entity;
    }
}
