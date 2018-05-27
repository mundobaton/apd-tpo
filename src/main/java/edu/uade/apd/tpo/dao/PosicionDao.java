package edu.uade.apd.tpo.dao;

import edu.uade.apd.tpo.entity.PosicionEntity;
import edu.uade.apd.tpo.model.Posicion;

import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

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

	public PosicionEntity findByCodigo(String codigo){
		String query = "select p from PosicionEntity p where p.codUbicacion = :codigo";
		try (Session session = getSession()) {
			Query<PosicionEntity> q = session.createQuery(query);
			q.setParameter("codigo", codigo);
			List<PosicionEntity> result = q.getResultList();
			return result.isEmpty() ? null : result.get(0);
		}
	}

	public List<Posicion> obtenerObtenerPosicionesVacias() {
		// TODO Auto-generated method stub
		return null;
	}

	public Posicion findByUbicacion(String ubicacion) {
		// TODO Auto-generated method stub
		return null;
	}
}
