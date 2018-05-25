package edu.uade.apd.tpo.dao;

import edu.uade.apd.tpo.entity.DomicilioEntity;

public class DomicilioDao extends AbstractDao<DomicilioEntity> {

	private static DomicilioDao instance;
	
	private DomicilioDao(){
		
	}
	
	public static DomicilioDao getInstance() {
		if(instance == null) {
			instance = new DomicilioDao();
		}
		return instance;
	}
}
