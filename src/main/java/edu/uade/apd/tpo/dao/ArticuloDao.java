package edu.uade.apd.tpo.dao;

import edu.uade.apd.tpo.entity.ArticuloEntity;
import edu.uade.apd.tpo.model.Articulo;

import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class ArticuloDao extends AbstractDao<ArticuloEntity> {

	private static ArticuloDao instance;
	
	private ArticuloDao(){
		
	}
	
	public static ArticuloDao getInstance() {
		if(instance == null) {
			instance = new ArticuloDao();
		}
		return instance;
	}

	public ArticuloEntity findByCodigo(String codigo){
		String query = "select p from ArticuloEntity p where p.codBarras = :codigo";
		try (Session session = getSession()) {
			Query<ArticuloEntity> q = session.createQuery(query);
			q.setParameter("codigo", codigo);
			List<ArticuloEntity> result = q.getResultList();
			return result.isEmpty() ? null : result.get(0);
		}
	}

	public Articulo findById(Long articuloId) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Articulo> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
