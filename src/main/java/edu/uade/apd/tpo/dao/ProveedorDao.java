package edu.uade.apd.tpo.dao;

import edu.uade.apd.tpo.entity.ProveedorEntity;

public class ProveedorDao extends AbstractDao<ProveedorEntity> {

	private static ProveedorDao instance;
	
	private ProveedorDao(){
		
	}
	
	public static ProveedorDao getInstance() {
		if(instance == null) {
			instance = new ProveedorDao();
		}
		return instance;
	}
}

