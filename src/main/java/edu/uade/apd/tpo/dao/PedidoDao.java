package edu.uade.apd.tpo.dao;

import edu.uade.apd.tpo.entity.PedidoEntity;

public class PedidoDao  extends AbstractDao<PedidoEntity> {

	private static PedidoDao instance;
	
	private PedidoDao(){
		
	}
	
	public static PedidoDao getInstance() {
		if(instance == null) {
			instance = new PedidoDao();
		}
		return instance;
	}
}