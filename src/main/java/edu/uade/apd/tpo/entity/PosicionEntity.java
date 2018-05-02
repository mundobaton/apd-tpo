package edu.uade.apd.tpo.entity;

import edu.uade.apd.tpo.model.EstadoPosicion;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "posiciones")
public class PosicionEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "posicion_id")
    private Long id;
    @Column(name = "codigo_ubicacion")
    private String codigoUbicacion;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "estado")
    private EstadoPosicion estado;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "lote_id")
    private LoteEntity lote;
    @Column(name = "cantidad")
    private int cantidad;
    @Column(name = "calle")
    private char calle;
    @Column(name = "bloque")
    private int bloque;
    @Column(name = "estanteria")
    private int estanteria;
    @Column(name = "estante")
    private int estante;
    @Column(name = "numero")
    private int numero;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigoUbicacion() {
        return codigoUbicacion;
    }

    public void setCodigoUbicacion(String codigoUbicacion) {
        this.codigoUbicacion = codigoUbicacion;
    }

    public EstadoPosicion getEstado() {
        return estado;
    }

    public void setEstado(EstadoPosicion estado) {
        this.estado = estado;
    }

    public LoteEntity getLote() {
        return lote;
    }

    public void setLote(LoteEntity lote) {
        this.lote = lote;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public char getCalle() {
        return calle;
    }

    public void setCalle(char calle) {
        this.calle = calle;
    }

    public int getBloque() {
        return bloque;
    }

    public void setBloque(int bloque) {
        this.bloque = bloque;
    }

    public int getEstanteria() {
        return estanteria;
    }

    public void setEstanteria(int estanteria) {
        this.estanteria = estanteria;
    }

    public int getEstante() {
        return estante;
    }

    public void setEstante(int estante) {
        this.estante = estante;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
}
