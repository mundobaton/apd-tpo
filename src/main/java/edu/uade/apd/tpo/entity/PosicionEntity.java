package edu.uade.apd.tpo.entity;

<<<<<<< HEAD
import java.io.Serializable;

import edu.uade.apd.tpo.model.EstadoPosicion;

=======
import edu.uade.apd.tpo.model.EstadoPosicion;

import javax.persistence.CascadeType;
>>>>>>> develop
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
<<<<<<< HEAD
import javax.persistence.ManyToOne;
=======
import javax.persistence.OneToOne;
>>>>>>> develop
import javax.persistence.Table;

@Entity
@Table(name = "posiciones")
<<<<<<< HEAD
public class PosicionEntity implements Serializable {
=======
public class PosicionEntity extends BaseEntity {

>>>>>>> develop
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "posicion_id")
    private Long id;
    @Column(name = "codigo_ubicacion")
<<<<<<< HEAD
    private String codUbicacion;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "estado_id")
    private EstadoPosicion estado;
=======
    private String codigoUbicacion;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "estado")
    private EstadoPosicion estado;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "lote_id")
    private LoteEntity lote;
>>>>>>> develop
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

<<<<<<< HEAD
    public String getCodUbicacion() {
        return codUbicacion;
    }

    public void setCodUbicacion(String codUbicacion) {
        this.codUbicacion = codUbicacion;
=======
    public String getCodigoUbicacion() {
        return codigoUbicacion;
    }

    public void setCodigoUbicacion(String codigoUbicacion) {
        this.codigoUbicacion = codigoUbicacion;
>>>>>>> develop
    }

    public EstadoPosicion getEstado() {
        return estado;
    }

    public void setEstado(EstadoPosicion estado) {
        this.estado = estado;
    }

<<<<<<< HEAD
=======
    public LoteEntity getLote() {
        return lote;
    }

    public void setLote(LoteEntity lote) {
        this.lote = lote;
    }

>>>>>>> develop
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
<<<<<<< HEAD


=======
>>>>>>> develop
}
