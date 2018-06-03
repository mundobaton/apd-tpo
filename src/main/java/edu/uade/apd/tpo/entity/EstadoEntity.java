package edu.uade.apd.tpo.entity;

<<<<<<< HEAD
import java.io.Serializable;
import java.util.Date;

=======
>>>>>>> develop
import edu.uade.apd.tpo.model.EstadoPedido;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
<<<<<<< HEAD

@Entity
@Table(name = "estados")
public class EstadoEntity implements Serializable {
=======
import java.util.Date;

@Entity
@Table(name = "estados")
public class EstadoEntity extends BaseEntity {
>>>>>>> develop

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "estado_id")
    private Long id;
<<<<<<< HEAD
    @Column(name = "estado_pedido_id")
    @Enumerated(EnumType.ORDINAL)
    private EstadoPedido estado;
=======
    @Column(name = "estado_pedido")
    @Enumerated(EnumType.ORDINAL)
    private EstadoPedido estadoPedido;
>>>>>>> develop
    @Column(name = "fecha")
    private Date fecha;
    @Column(name = "motivo")
    private String motivo;
<<<<<<< HEAD
=======
    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private PedidoEntity pedido;
>>>>>>> develop

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

<<<<<<< HEAD
    public EstadoPedido getEstado() {
        return estado;
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
=======
    public EstadoPedido getEstadoPedido() {
        return estadoPedido;
    }

    public void setEstadoPedido(EstadoPedido estadoPedido) {
        this.estadoPedido = estadoPedido;
>>>>>>> develop
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
<<<<<<< HEAD
=======

    public PedidoEntity getPedido() {
        return pedido;
    }

    public void setPedido(PedidoEntity pedido) {
        this.pedido = pedido;
    }
>>>>>>> develop
}
