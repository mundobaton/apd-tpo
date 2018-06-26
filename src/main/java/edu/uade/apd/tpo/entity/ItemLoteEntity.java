package edu.uade.apd.tpo.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "item_lotes")
public class ItemLoteEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_lote_id")
    private Long id;
    @Column(name = "codigo")
    private String codigo;
    @Column(name = "fecha_vencimiento")
    private Date fechaVencimiento;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "item_lote_id")
    private List<UbicacionEntity> ubicaciones;

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

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public List<UbicacionEntity> getUbicaciones() {
        return ubicaciones;
    }

    public void setUbicaciones(List<UbicacionEntity> ubicaciones) {
        this.ubicaciones = ubicaciones;
    }
}
