package edu.uade.apd.tpo.model;

import java.util.List;

import edu.uade.apd.tpo.dao.ClienteDao;

public class Cliente extends Usuario{

	private String nombre;
	private Long cuil;
	private String telefono;
	private Domicilio domicilio;
	private CondicionIva condIva;
	private CuentaCorriente cuentaCorriente;
	private List<Pedido> pedidos;

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

	public Domicilio getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(Domicilio domicilio) {
		this.domicilio = domicilio;
	}

	public CondicionIva getCondIva() {
		return condIva;
	}

	public void setCondIva(CondicionIva condIva) {
		this.condIva = condIva;
	}

	public CuentaCorriente getCuentaCorriente() {
		return cuentaCorriente;
	}

	public void setCuentaCorriente(CuentaCorriente cuentaCorriente) {
		this.cuentaCorriente = cuentaCorriente;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public void guardar() {
		ClienteDao.getInstance().save(this);
	}

}
