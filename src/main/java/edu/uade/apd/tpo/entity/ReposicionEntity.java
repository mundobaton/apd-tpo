package edu.uade.apd.tpo.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "reposiciones")
public class ReposicionEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reposicion_id")
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "orden_compra_id")
    private OrdenCompraEntity ordenCompra;
    @Column(name = "cantidad")
    private int cantidad;
    @Column(name = "estado")
    private char estado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }

    public OrdenCompraEntity getOrdenCompra() {
        return ordenCompra;
    }

    public void setOrdenCompra(OrdenCompraEntity ordenCompra) {
        this.ordenCompra = ordenCompra;
    }
}
