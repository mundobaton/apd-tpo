package edu.uade.apd.tpo.dao;

import edu.uade.apd.tpo.entity.FacturaEntity;
import edu.uade.apd.tpo.model.Factura;

public class FacturaDao extends AbstractDao<FacturaEntity> {

	private static FacturaDao instance;
	
	private FacturaDao(){
		
	}
	
	public static FacturaDao getInstance() {
		if(instance == null) {
			instance = new FacturaDao();
		}
		return instance;
	}

	public Factura findById(Long facturaId) {
		// TODO Auto-generated method stub
		return null;
	}
}
