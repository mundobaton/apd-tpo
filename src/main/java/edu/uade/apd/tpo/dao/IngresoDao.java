package edu.uade.apd.tpo.dao;

import edu.uade.apd.tpo.entity.IngresoEntity;

public class IngresoDao extends AbstractDao<IngresoEntity> {

	private static IngresoDao instance;
	
	private IngresoDao(){
		
	}
	
	public static IngresoDao getInstance() {
		if(instance == null) {
			instance = new IngresoDao();
		}
		return instance;
	}
}
