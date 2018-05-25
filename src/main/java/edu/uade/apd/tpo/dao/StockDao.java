package edu.uade.apd.tpo.dao;

import edu.uade.apd.tpo.entity.StockEntity;

public class StockDao extends AbstractDao<StockEntity> {

	private static StockDao instance;
	
	private StockDao(){
		
	}
	
	public static StockDao getInstance() {
		if(instance == null) {
			instance = new StockDao();
		}
		return instance;
	}
}

