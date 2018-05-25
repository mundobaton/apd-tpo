package edu.uade.apd.tpo.model;

public class Ingreso extends Movimiento {
	
	private MotivoIngreso motivo;

	
	public MotivoIngreso getMotivo() {
		return motivo;
	}

	public void setMotivo(MotivoIngreso motivo) {
		this.motivo = motivo;
	}
	
	public void guardar() {
		
	}
	
	public int cantidad() {
		return this.cantidad;
	}
	 
	
}
