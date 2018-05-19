package edu.uade.apd.tpo.controller;

import edu.uade.apd.tpo.model.Articulo;
import edu.uade.apd.tpo.model.OrdenCompra;

public class SistemaCompras {

    private static SistemaCompras instance;

    private SistemaCompras() {
    }

    public static SistemaCompras getInstance() {
        if (instance == null) {
            instance = new SistemaCompras();
        }
        return instance;
    }

    public void generarOrdenCompra(Long articuloId) {
        Articulo art = SistemaDeposito.getInstance().buscarArticulo(articuloId);
        OrdenCompra oc = new OrdenCompra(art);
    }

}
