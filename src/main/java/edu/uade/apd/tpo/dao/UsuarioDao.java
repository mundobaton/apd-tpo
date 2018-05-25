package edu.uade.apd.tpo.dao;

import edu.uade.apd.tpo.entity.UsuarioEntity;

public class UsuarioDao extends AbstractDao<UsuarioEntity> {

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
