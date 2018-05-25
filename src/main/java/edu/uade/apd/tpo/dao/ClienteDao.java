package edu.uade.apd.tpo.dao;

import edu.uade.apd.tpo.entity.ClienteEntity;

public class ClienteDao extends AbstractDao<ClienteEntity> {

	private static ClienteDao instance;
	
	private ClienteDao(){
		
	}
	
	public static ClienteDao getInstance() {
		if(instance == null) {
			instance = new ClienteDao();
		}
		return instance;
	}
}
