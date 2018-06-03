package edu.uade.apd.tpo.dao;

import edu.uade.apd.tpo.entity.TransaccionEntity;

public class TransaccionDao extends AbstractDao<TransaccionEntity> {

	private static TransaccionDao instance;
	
	private TransaccionDao(){
		
	}
	
	public static TransaccionDao getInstance() {
		if(instance == null) {
			instance = new TransaccionDao();
		}
		return instance;
	}
}