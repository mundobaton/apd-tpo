package edu.uade.apd.tpo.dao;

import edu.uade.apd.tpo.entity.ClienteEntity;

public class UsuarioDao extends AbstractDao<ClienteEntity> {

	private static UsuarioDao instance;
	
	private UsuarioDao(){
		
	}
	
	public static UsuarioDao getInstance() {
		if(instance == null) {
			instance = new UsuarioDao();
		}
		return instance;
	}
}
