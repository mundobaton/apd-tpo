package edu.uade.apd.tpo.model;

<<<<<<< HEAD
import edu.uade.apd.tpo.repository.stub.TransportistaStub;

public enum Transportista {
	OCA (20),
=======
public enum Transportista {

    OCA (20),
>>>>>>> develop
    CORREO_ARGENTINO (25),
    ANDREANI (28);

    private float precio;
	
	Transportista(float precio) {
		this.precio = precio;
	}
	
	public float getPrecio() {
		return this.precio;
	}
<<<<<<< HEAD

	public static Transportista fromStub(TransportistaStub stub) {
		return Transportista.valueOf(stub.name());
	}

	public TransportistaStub toStub() {
		return TransportistaStub.valueOf(name());
	}
=======
>>>>>>> develop
}
