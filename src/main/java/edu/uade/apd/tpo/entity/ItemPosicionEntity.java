package edu.uade.apd.tpo.entity;

import java.io.Serializable;

import edu.uade.apd.tpo.model.Posicion;

import javax.persistence.*;

@Entity
@Table(name = "item_posiciones")
public class ItemPosicionEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "item_posicion_id")
	private Long id;
	@Column(name = "cantidad")
	private int cantidad;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "posicion_id")
	private PosicionEntity posicion;

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

	public PosicionEntity getPosicion() {
		return posicion;
	}

	public void setPosicion(PosicionEntity posicion) {
		this.posicion = posicion;
	}

}
