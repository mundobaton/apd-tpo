package edu.uade.apd.tpo.dao;

import edu.uade.apd.tpo.entity.ArticuloEntity;

public class ArticuloDao extends AbstractDao<ArticuloEntity> {

	private static ArticuloDao instance;
	
	private ArticuloDao(){
		
	}
	
	public static ArticuloDao getInstance() {
		if(instance == null) {
			instance = new ArticuloDao();
		}
		return instance;
	}
}
