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
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "lotes")
public class LoteEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lote_id")
    private Long id;
    @Column(name = "codigo")
    private String codigo;
    @Column(name = "fecha_vencimiento")
    private Date fechaVto;
    @Column(name = "fecha_elab")
    private Date fechaElaboracion;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "articulo_id")
    private ArticuloEntity articulo;
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

    public ArticuloEntity getArticulo() {
        return articulo;
    }

    public void setArticulo(ArticuloEntity articulo) {
        this.articulo = articulo;
    }

    public List<PosicionEntity> getPosiciones() {
        return posiciones;
    }

    public void setPosiciones(List<PosicionEntity> posiciones) {
        this.posiciones = posiciones;
    }
}
