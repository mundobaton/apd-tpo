package edu.uade.apd.tpo.entity;

import java.io.Serializable;
import java.util.Date;

import edu.uade.apd.tpo.model.EstadoCompra;

import javax.persistence.*;

@Entity
@Table(name="ordenes_compra")
public class OrdenCompraEntity implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="orden_compra_id")
	private Long id;
	@OneToOne
	@JoinColumn(name = "articulo_id")
	private ArticuloEntity articulo;
	@Column(name = "estado_id")
	@Enumerated(EnumType.ORDINAL)
	private EstadoCompra estado;
	@OneToOne
	@JoinColumn(name = "proveedor_id")
	private ProveedorEntity proveedor;
	@Column(name = "fecha")
	private Date fecha;
	@OneToOne
	@JoinColumn(name = "pedido_id")
	private PedidoEntity pedido;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public ArticuloEntity getArticulo() {
		return articulo;
	}
	public void setArticulo(ArticuloEntity articulo) {
		this.articulo = articulo;
	}
	public EstadoCompra getEstado() {
		return estado;
	}
	public void setEstado(EstadoCompra estado) {
		this.estado = estado;
	}
	public ProveedorEntity getProveedor() {
		return proveedor;
	}
	public void setProveedor(ProveedorEntity proveedor) {
		this.proveedor = proveedor;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public PedidoEntity getPedido() {
		return pedido;
	}
	public void setPedido(PedidoEntity pedido) {
		this.pedido = pedido;
	}

}
