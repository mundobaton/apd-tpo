package edu.uade.apd.tpo.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "pedidos")
public class PedidoEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pedido_id")
    private Long id;
    @Column(name = "fecha_pedido")
    private Date fechaPedido;
    @Column(name = "fecha_entrega")
    private Date fechaEntrega;
    @Column(name = "fecha_despacho")
    private Date fechaDepacho;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "pedido_id")
    private List<ItemPedidoEntity> items;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "pedido_id")
    private List<EstadoEntity> estados;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "envio_id")
    private EnvioEntity envio;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "factura_id")
    private FacturaEntity factura;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cliente_id")
    private ClienteEntity cliente;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(Date fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public Date getFechaDepacho() {
        return fechaDepacho;
    }

    public void setFechaDepacho(Date fechaDepacho) {
        this.fechaDepacho = fechaDepacho;
    }

    public List<ItemPedidoEntity> getItems() {
        return items;
    }

    public void setItems(List<ItemPedidoEntity> items) {
        this.items = items;
    }

    public List<EstadoEntity> getEstados() {
        return estados;
    }

    public void setEstados(List<EstadoEntity> estados) {
        this.estados = estados;
    }

    public EnvioEntity getEnvio() {
        return envio;
    }

    public void setEnvio(EnvioEntity envio) {
        this.envio = envio;
    }

    public FacturaEntity getFactura() {
        return factura;
    }

    public void setFactura(FacturaEntity factura) {
        this.factura = factura;
    }

    public ClienteEntity getCliente() {
        return cliente;
    }

    public void setCliente(ClienteEntity cliente) {
        this.cliente = cliente;
    }
}
