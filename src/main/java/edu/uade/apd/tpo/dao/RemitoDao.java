package edu.uade.apd.tpo.dao;

import edu.uade.apd.tpo.entity.RemitoEntity;

public class RemitoDao extends AbstractDao<RemitoEntity> {

	private static RemitoDao instance;
	
	private RemitoDao(){
		
	}
	
	public static RemitoDao getInstance() {
		if(instance == null) {
			instance = new RemitoDao();
		}
		return instance;
	}
}
