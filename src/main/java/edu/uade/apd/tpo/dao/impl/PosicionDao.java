package edu.uade.apd.tpo.dao.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Session;
import org.hibernate.query.Query;

import edu.uade.apd.tpo.entity.FacturaEntity;
import edu.uade.apd.tpo.entity.PedidoEntity;
import edu.uade.apd.tpo.entity.PosicionEntity;
import edu.uade.apd.tpo.model.EstadoPosicion;
import edu.uade.apd.tpo.model.Factura;
import edu.uade.apd.tpo.model.Lote;
import edu.uade.apd.tpo.model.Pedido;
import edu.uade.apd.tpo.model.Posicion;
import edu.uade.apd.tpo.remote.TransformUtils;

public class PosicionDao extends AbstractDao<PosicionEntity> {
	
	private static PosicionDao instance;
	
	private PosicionDao() {
    }

    public static PosicionDao getInstance() {
        if (instance == null) {
            instance = new PosicionDao();
        }
        return instance;
    }
    
    public Posicion findById(String codigoUbicacion) {
        String query = "select f from PosicionEntity where id = :codigoUbicacion";

        try (Session session = getSession()) {
            Query<PosicionEntity> q = session.createQuery(query).setParameter("codigoUbicacion", codigoUbicacion);
            PosicionEntity result = q.getSingleResult();
            return TransformUtils.to(result, Posicion.class);
        }
    }
    
    public List<Posicion> obtenerObtenerPosicionesVacias() {
        String query = "select f from PosicionEntity" +
                	   "where f.estado = :DISPONIBLE";
        try (Session session = getSession()) {
            Query<PosicionEntity> q = session.createQuery(query);
            List<PosicionEntity> entities = q.getResultList();
            return entities.parallelStream().map(u -> TransformUtils.to(u, Posicion.class)).collect(Collectors.toList());
        }
    }
    
    public void save(Posicion posicion) {
    	PosicionEntity entity = TransformUtils.to(posicion, PosicionEntity.class);
        super.save(entity);
    }
}
