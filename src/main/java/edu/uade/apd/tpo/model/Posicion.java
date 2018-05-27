package edu.uade.apd.tpo.model;

import edu.uade.apd.tpo.entity.PosicionEntity;

public class Posicion {
	private Long id;
	private String codUbicacion;
	private EstadoPosicion estado;
	private Lote lote;
	private int cantidad;
	private char calle;
	private int bloque;
	private int estanteria;
	private int estante;
	private int numero;
	private static int CAPACIDAD = 100;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodUbicacion() {
		return codUbicacion;
	}

	public void setCodUbicacion(String codUbicacion) {
		this.codUbicacion = codUbicacion;
	}

	public EstadoPosicion getEstado() {
		return estado;
	}

	public void setEstado(EstadoPosicion estado) {
		this.estado = estado;
	}

	public Lote getLote() {
		return lote;
	}

	public void setLote(Lote lote) {
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

	public void guardar() {

	}
	
	public void liberar(int cantidad) {
		this.cantidad -= cantidad;
	}

	public static int getCAPACIDAD() {
		return CAPACIDAD;
	}

	public static void setCAPACIDAD(int cAPACIDAD) {
		CAPACIDAD = cAPACIDAD;
	}

	public static Posicion fromEntity(PosicionEntity entity) {
		Posicion p = null;
		if(entity != null){
			p.setId(entity.getId());
			p.setBloque(entity.getBloque());
			p.setCalle(entity.getCalle());
			p.setCantidad(entity.getCantidad());
			p.setCodUbicacion(entity.getCodUbicacion());
			p.setEstante(entity.getEstante());
			p.setEstado(entity.getEstado());
			p.setEstanteria(entity.getEstanteria());
			p.setLote(Lote.fromEntity(entity.getLote()));
			p.setNumero(entity.getNumero());
		}
		return p;
	}

	public PosicionEntity toEntity(){
		PosicionEntity entity = new PosicionEntity();
		entity.setId(id);
		entity.setBloque(bloque);
		entity.setCalle(calle);
		entity.setCantidad(cantidad);
		entity.setCodUbicacion(codUbicacion);
		entity.setEstante(estante);
		entity.setEstado(estado);
		entity.setEstanteria(estanteria);
		entity.setLote(lote.toEntity());
		entity.setNumero(numero);
		return entity;

	}

}
