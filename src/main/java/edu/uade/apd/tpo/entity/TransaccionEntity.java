package edu.uade.apd.tpo.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import edu.uade.apd.tpo.model.Factura;
import edu.uade.apd.tpo.model.MedioPago;

public class TransaccionEntity implements Serializable{
	
	private Long id;
	private float importe;
	private Date fecha;
	private List<Factura> facturas;
	private MedioPago medioPago;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public float getImporte() {
		return importe;
	}
	public void setImporte(float importe) {
		this.importe = importe;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public List<Factura> getFacturas() {
		return facturas;
	}
	public void setFacturas(List<Factura> facturas) {
		this.facturas = facturas;
	}
	public MedioPago getMedioPago() {
		return medioPago;
	}
	public void setMedioPago(MedioPago medioPago) {
		this.medioPago = medioPago;
	}

	

}
