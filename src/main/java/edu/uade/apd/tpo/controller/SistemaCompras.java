package edu.uade.apd.tpo.controller;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.uade.apd.tpo.dao.impl.OrdenCompraDao;
import edu.uade.apd.tpo.model.Articulo;
import edu.uade.apd.tpo.model.EstadoCompra;
import edu.uade.apd.tpo.model.OrdenCompra;
import edu.uade.apd.tpo.model.Proveedor;

public class SistemaCompras {

    private static SistemaCompras instance;
    private OrdenCompraDao ordenCompraDao;

    private SistemaCompras() {
    	this.ordenCompraDao = OrdenCompraDao.getInstance();
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
        oc.setEstado(EstadoCompra.PENDIENTE);
        oc.guardar();
    }
    
    public OrdenCompra buscarOrdenCompra(Long ordenId) {
    	return ordenCompraDao.findById(ordenId);
    }
    
    public List<OrdenCompra> buscarOrdenesPorArticulo(Articulo art){
    	List<OrdenCompra> ordenes = ordenCompraDao.findAll();
    	List<OrdenCompra> ordenesArticulo = new ArrayList<>();
    	for (OrdenCompra ordenCompra : ordenes) {
			if(ordenCompra.getArticulo().getId() == art.getId()) {
				if(ordenesArticulo.isEmpty()) {
					ordenesArticulo.add(ordenCompra);
				}else {
					if(ordenesArticulo.get(0).getFecha().compareTo(ordenCompra.getFecha()) <= 0) {
						OrdenCompra temp1 = ordenesArticulo.get(0);
						OrdenCompra temp2 = ordenesArticulo.get(1);
						ordenesArticulo.add(0, ordenCompra);
						ordenesArticulo.add(1, temp1);
						ordenesArticulo.add(2, temp2);
					}else {
						if(ordenesArticulo.get(1) == null) {
							ordenesArticulo.add(1, ordenCompra);
						}else{
							if(ordenesArticulo.get(1).getFecha().compareTo(ordenCompra.getFecha()) <= 0) {
								OrdenCompra temp = ordenesArticulo.get(1);
								ordenesArticulo.add(ordenCompra);
								ordenesArticulo.add(2, temp);
							}else {
								if(ordenesArticulo.get(2) == null || ordenesArticulo.get(2).getFecha().compareTo(ordenCompra.getFecha()) <= 0) {
									ordenesArticulo.add(2, ordenCompra);
								}
							}
						}
					}
				}
			}
		}
    	return ordenesArticulo;
    }
    
    public Proveedor obtenerProveedor(List<OrdenCompra> ordenes){
    	Random random = new Random();
    	int index = random.nextInt(ordenes.size());
    	OrdenCompra orden = ordenes.get(index);
    	return orden.getProveedor();
    }
    
    public void procesarOrdenesCompraPendientes() {
    	List<OrdenCompra> ordenesPendientes = ordenCompraDao.getInstance().findAllPendientes();
    	for (OrdenCompra ordenCompra : ordenesPendientes) {
    		procesarOrdenCompra(ordenCompra);
		}
    }
    
    public void procesarOrdenCompra(OrdenCompra orden){
    	Articulo art = orden.getArticulo();
    	List<OrdenCompra> ordenes = buscarOrdenesPorArticulo(art);
    	Proveedor proveedor = obtenerProveedor(ordenes);
    	orden.setEstado(EstadoCompra.GENERADO);
    	orden.setFecha(new Date());
    	orden.setProveedor(proveedor);
    	orden.guardar();
    }
    
    public void aceptarOrdenCompra(Long ordenId) {
    	OrdenCompra orden = buscarOrdenCompra(ordenId);
    	orden.setEstado(EstadoCompra.COMPLETO);
    	orden.guardar();
    }

}
