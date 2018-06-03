package edu.uade.apd.tpo.dao;

import edu.uade.apd.tpo.entity.EstadoEntity;

public class EstadoDao extends AbstractDao<EstadoEntity> {

	private static EstadoDao instance;
	
	private EstadoDao(){
		
	}
	
	public static EstadoDao getInstance() {
		if(instance == null) {
			instance = new EstadoDao();
		}
		return instance;
	}
}
