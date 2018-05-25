package edu.uade.apd.tpo.entity;

import java.util.List;

public class CuentaCorrienteEntity {
	
	 private Long id;
	 private float saldo;
	 private float limiteCredito;
	 private List<TransaccionEntity> transacciones;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public float getSaldo() {
		return saldo;
	}
	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}
	public float getLimiteCredito() {
		return limiteCredito;
	}
	public void setLimiteCredito(float limiteCredito) {
		this.limiteCredito = limiteCredito;
	}
	public List<TransaccionEntity> getTransacciones() {
		return transacciones;
	}
	public void setTransacciones(List<TransaccionEntity> transacciones) {
		this.transacciones = transacciones;
	}
}
