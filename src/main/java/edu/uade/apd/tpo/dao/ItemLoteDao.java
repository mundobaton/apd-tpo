package edu.uade.apd.tpo.dao;

import edu.uade.apd.tpo.entity.ItemLoteEntity;

public class ItemLoteDao extends AbstractDao<ItemLoteEntity> {

	private static ItemLoteDao instance;
	
	private ItemLoteDao(){
		
	}
	
	public static ItemLoteDao getInstance() {
		if(instance == null) {
			instance = new ItemLoteDao();
		}
		return instance;
	}
}
