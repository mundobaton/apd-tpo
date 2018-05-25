package edu.uade.apd.tpo.entity;

import java.io.Serializable;

import edu.uade.apd.tpo.model.MotivoIngreso;

public class IngresoEntity implements Serializable {
	private MotivoIngreso motivo;

	public MotivoIngreso getMotivo() {
		return motivo;
	}

	public void setMotivo(MotivoIngreso motivo) {
		this.motivo = motivo;
	}
	
}
