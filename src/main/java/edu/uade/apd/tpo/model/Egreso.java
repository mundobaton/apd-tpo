package edu.uade.apd.tpo.model;

public class Egreso  extends Movimiento {
	private MotivoEgreso motivo;
	private Cliente encargado;
	private String autorizante;
	private String destino;
	
	
	
	public MotivoEgreso getMotivo() {
		return motivo;
	}
	public void setMotivo(MotivoEgreso motivo) {
		this.motivo = motivo;
	}
	
	public Cliente getEncargado() {
		return encargado;
	}
	public void setEncargado(Cliente encargado) {
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
	
	public void guardar() {

	}
	
	public int cantidad() {
		return	this.cantidad;
		}
	
	
}
