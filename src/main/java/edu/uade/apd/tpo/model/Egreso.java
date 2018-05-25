package edu.uade.apd.tpo.model;

public class Egreso {
	private MotivoEgreso motivo;
	private Usuario encargado;
	private String autorizante;
	private String destino;
	
	
	
	public MotivoEgreso getMotivo() {
		return motivo;
	}
	public void setMotivo(MotivoEgreso motivo) {
		this.motivo = motivo;
	}
	
	public Usuario getEncargado() {
		return encargado;
	}
	public void setEncargado(Usuario encargado) {
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
