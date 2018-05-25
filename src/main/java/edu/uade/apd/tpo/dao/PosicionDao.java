package edu.uade.apd.tpo.dao;

import edu.uade.apd.tpo.entity.PosicionEntity;

public class PosicionDao  extends AbstractDao<PosicionEntity> {

	private static PosicionDao instance;
	
	private PosicionDao(){
		
	}
	
	public static PosicionDao getInstance() {
		if(instance == null) {
			instance = new PosicionDao();
		}
		return instance;
	}
}
