package edu.uade.apd.tpo.dao;

import edu.uade.apd.tpo.entity.EgresoEntity;;

public class EgresoDao extends AbstractDao<EgresoEntity> {

	private static EgresoDao instance;
	
	private EgresoDao(){
		
	}
	
	public static EgresoDao getInstance() {
		if(instance == null) {
			instance = new EgresoDao();
		}
		return instance;
	}
}
