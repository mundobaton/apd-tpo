package edu.uade.apd.tpo.model;

import java.util.Arrays;

public enum EstadoCompra {
	PENDIENTE ("Pendiente"),
	EMITIDA ("Emitida"),
	ACEPTADA ("Aceptada");

	private String value;

	EstadoCompra(String val) {
		this.value = val;
	}

	public static EstadoCompra getEstadoCompraFromValue(String value) {
		return Arrays.asList(EstadoCompra.values()).stream().filter(c -> c.value.equals(value)).findFirst().get();
	}

	public String getValue(){
		return this.value;
	}
}
