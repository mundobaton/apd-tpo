package edu.uade.apd.tpo.model;

import java.util.List;

import edu.uade.apd.tpo.dao.ItemPedidoDao;

public class ItemPedido {

	private Long id;
	private Articulo articulo;
	private int cantidad;
	private float subTotal;
	private List<ItemLote> lotes;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Articulo getArticulo() {
		return articulo;
	}

	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public float getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(float subTotal) {
		this.subTotal = subTotal;
	}

	public List<ItemLote> getLotes() {
		return lotes;
	}

	public void setLotes(List<ItemLote> lotes) {
		this.lotes = lotes;
	}

	public void actualizar(int cant) {
		this.cantidad += cant; 
	}
	
	public void agregarLote(Lote lote, int cantidad) {
		int index = 0;
		boolean salir = true;
		if(this.lotes.get(index) == null) {
			ItemLote itemLote = new ItemLote();
			itemLote.setLote(lote);
			itemLote.setCantidad(cantidad);
			salir = false;
		}
		while(this.lotes.get(index) != null && salir == true) {
			if(this.lotes.get(index).getLote().getId() == lote.getId())){
				int cantAnterior = this.lotes.get(index).getCantidad();
				this.lotes.get(index).setCantidad(cantidad + cantAnterior);
				salir = false;
			}
		}
	}
	
	public void guardar() {
		ItemPedidoDao.getInstance().save(this);
	}

}
