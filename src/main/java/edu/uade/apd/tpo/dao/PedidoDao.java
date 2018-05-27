package edu.uade.apd.tpo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import edu.uade.apd.tpo.entity.PedidoEntity;
import edu.uade.apd.tpo.model.Pedido;

public class PedidoDao extends AbstractDao<PedidoEntity> {

	private static PedidoDao instance;

	private PedidoDao() {

	}

	public static PedidoDao getInstance() {
		if (instance == null) {
			instance = new PedidoDao();
		}
		return instance;
	}

	public PedidoEntity findById(Long pedidoId) {
		String query = "select p from PedidoEntity p where p.id = :pedidoId";

		try (Session session = getSession()) {
			Query<PedidoEntity> q = session.createQuery(query).setParameter("pedidoId", pedidoId);
			PedidoEntity result = q.getSingleResult();
			return result;
		}
	}

	public void save(PedidoEntity pedido) {
		super.save(pedido);
	}
    
    public List<PedidoEntity> obtenerPedidosCompletos(){
       
    	String query = "select p from PedidoEntity p inner join p.estados as e " +
                "where e.estadoPedido = 'COMPLETO'";
        try (Session session = getSession()) {
            Query<PedidoEntity> q = session.createQuery(query);
            List<PedidoEntity> entities = q.getResultList();
            return entities;
        } 
    	
    }

	public List<Pedido> obtenerPedidosPreAprobadosRevision() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Pedido> obtenerPedidosListos() {
		// TODO Auto-generated method stub
		return null;
	}





}