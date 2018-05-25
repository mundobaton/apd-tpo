package edu.uade.apd.tpo.entity;

import java.util.List;

import edu.uade.apd.tpo.model.CondicionIva;

public class UsuarioEntity {
	
	private String nombre;
	private Long cuil;
	private String telefono;
	private DomicilioEntity domicilio;
	private CondicionIva condIva;
    private CuentaCorrienteEntity cuentaCorriente;
	private List<PedidoEntity> pedidos;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Long getCuil() {
		return cuil;
	}
	public void setCuil(Long cuil) {
		this.cuil = cuil;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public DomicilioEntity getDomicilio() {
		return domicilio;
	}
	public void setDomicilio(DomicilioEntity domicilio) {
		this.domicilio = domicilio;
	}
	public CondicionIva getCondIva() {
		return condIva;
	}
	public void setCondIva(CondicionIva condIva) {
		this.condIva = condIva;
	}
	public CuentaCorrienteEntity getCuentaCorriente() {
		return cuentaCorriente;
	}
	public void setCuentaCorriente(CuentaCorrienteEntity cuentaCorriente) {
		this.cuentaCorriente = cuentaCorriente;
	}
	public List<PedidoEntity> getPedidos() {
		return pedidos;
	}
	public void setPedidos(List<PedidoEntity> pedidos) {
		this.pedidos = pedidos;
	}
	 
	 
}
