package edu.uade.apd.tpo.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
<<<<<<< HEAD
import java.io.Serializable;
=======
>>>>>>> develop
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "lotes")
<<<<<<< HEAD
public class LoteEntity implements Serializable {
=======
public class LoteEntity extends BaseEntity {

>>>>>>> develop
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lote_id")
    private Long id;
    @Column(name = "codigo")
    private String codigo;
    @Column(name = "fecha_vencimiento")
    private Date fechaVto;
<<<<<<< HEAD
    @Column(name = "fecha_elaboracion")
    private Date fechaElaboracion;
=======
    @Column(name = "fecha_elab")
    private Date fechaElaboracion;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "articulo_id")
    private ArticuloEntity articulo;
>>>>>>> develop
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "lote_id")
    private List<PosicionEntity> posiciones;

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

<<<<<<< HEAD
=======
    public ArticuloEntity getArticulo() {
        return articulo;
    }

    public void setArticulo(ArticuloEntity articulo) {
        this.articulo = articulo;
    }

>>>>>>> develop
    public List<PosicionEntity> getPosiciones() {
        return posiciones;
    }

    public void setPosiciones(List<PosicionEntity> posiciones) {
        this.posiciones = posiciones;
    }
<<<<<<< HEAD


=======
>>>>>>> develop
}
