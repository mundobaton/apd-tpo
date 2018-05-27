package edu.uade.apd.tpo.model;

import java.util.Arrays;

public enum CondicionIva {

	RESP_INSCRIPTO("Responsable Inscripto"),
	EXENTO("Exento"),
	CONS_FINAL("Consumidor Final");

	private String value;

	CondicionIva(String val) {
		this.value = val;
	}

	public static CondicionIva getCondIvaFromValue(String value) {
		return Arrays.asList(CondicionIva.values()).stream().filter(c -> c.value.equals(value)).findFirst().get();
	}
}
