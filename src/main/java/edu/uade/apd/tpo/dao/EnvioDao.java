package edu.uade.apd.tpo.dao;

import edu.uade.apd.tpo.entity.EnvioEntity;

public class EnvioDao extends AbstractDao<EnvioEntity> {

	private static EnvioDao instance;
	
	private EnvioDao(){
		
	}
	
	public static EnvioDao getInstance() {
		if(instance == null) {
			instance = new EnvioDao();
		}
		return instance;
	}
}