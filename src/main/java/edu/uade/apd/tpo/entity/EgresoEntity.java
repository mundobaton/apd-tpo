package edu.uade.apd.tpo.entity;

import java.io.Serializable;

import edu.uade.apd.tpo.model.MotivoEgreso;

public class EgresoEntity implements Serializable {
	private MotivoEgreso motivo;
	private String encargado;
	private String autorizante;
	private String destino;
	public MotivoEgreso getMotivo() {
		return motivo;
	}
	public void setMotivo(MotivoEgreso motivo) {
		this.motivo = motivo;
	}
	public String getEncargado() {
		return encargado;
	}
	public void setEncargado(String encargado) {
		this.encargado = encargado;
	}
	public String getAutorizante() {
		return autorizante;
	}
	public void setAutorizante(String autorizante) {
		this.autorizante = autorizante;
	}
	public String getDestino() {
		return destino;
	}
	public void setDestino(String destino) {
		this.destino = destino;
	}
	
	
	
}
