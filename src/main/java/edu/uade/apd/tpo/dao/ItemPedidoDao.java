package edu.uade.apd.tpo.dao;

import edu.uade.apd.tpo.entity.ItemPedidoEntity;

public class ItemPedidoDao extends AbstractDao<ItemPedidoEntity> {

	private static ItemPedidoDao instance;
	
	private ItemPedidoDao(){
		
	}
	
	public static ItemPedidoDao getInstance() {
		if(instance == null) {
			instance = new ItemPedidoDao();
		}
		return instance;
	}
}