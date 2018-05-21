package edu.uade.apd.tpo.model;

public class Ingreso extends Movimiento {

    private MotivoIngreso motivoIngreso;

	public MotivoIngreso getMotivoIngreso() {
		return motivoIngreso;
	}

	public void setMotivoIngreso(MotivoIngreso motivoIngreso) {
		this.motivoIngreso = motivoIngreso;
	}
}
