package edu.uade.apd.tpo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.uade.apd.tpo.dao.impl.OrdenCompraDao;
import edu.uade.apd.tpo.model.Articulo;
import edu.uade.apd.tpo.model.ItemLote;
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
    }
    
    public OrdenCompra buscarOrdenCompra(Long ordenId) {
    	return ordenCompraDao.findById(ordenId);
    }
    
    //A OrdenCompra no le faltaria el atributo fecha para saber cuales son las ultimas?
    public List<OrdenCompra> buscarOrdenesPorArticulo(Articulo art){
    	List<OrdenCompra> ordenes = ordenCompraDao.findAll();
    	List<OrdenCompra> ordenesArticulo = new ArrayList<>();
    	for (OrdenCompra ordenCompra : ordenes) {
			if(ordenCompra.getArticulo().getId() == art.getId()) {
				ordenesArticulo.add(ordenCompra);
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
    
    public void procesarOrdenCompra(Long ordenId) {
    	OrdenCompra orden = buscarOrdenCompra(ordenId);
    	List<ItemLote> itemsLote = new ArrayList<>();
    	Articulo art = orden.getArticulo();
    	List<OrdenCompra> ordenes = buscarOrdenesPorArticulo(art);
    	Proveedor proveedor = obtenerProveedor(ordenes);
    	orden.setProveedor(proveedor);
    	
    	
    	SistemaDeposito.getInstance().almacenar(art.getId(), itemsLote, cantidad);  
    	ordenCompraDao.save(orden);
    }

}
