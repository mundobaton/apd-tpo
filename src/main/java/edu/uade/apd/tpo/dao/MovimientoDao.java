package edu.uade.apd.tpo.dao;

import edu.uade.apd.tpo.entity.MovimientoEntity;

public class MovimientoDao extends AbstractDao<MovimientoEntity> {

	private static MovimientoDao instance;
	
	private MovimientoDao(){
		
	}
	
	public static MovimientoDao getInstance() {
		if(instance == null) {
			instance = new MovimientoDao();
		}
		return instance;
	}
}
