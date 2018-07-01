package edu.uade.apd.tpo.dao;

import edu.uade.apd.tpo.entity.OrdenCompraEntity;
import edu.uade.apd.tpo.entity.PedidoEntity;
import edu.uade.apd.tpo.entity.UbicacionEntity;
import edu.uade.apd.tpo.model.OrdenCompra;
import edu.uade.apd.tpo.model.Pedido;
import edu.uade.apd.tpo.model.Ubicacion;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UbicacionDao extends AbstractDao<UbicacionEntity> {

    private static UbicacionDao instance;

    private UbicacionDao() {

    }

    public static UbicacionDao getInstance() {
        if (instance == null) {
            instance = new UbicacionDao();
        }
        return instance;
    }

    public List<Ubicacion> findByEstado(char estado) {
        String query = "select u from UbicacionEntity u where u.estado = :estado";
        try (Session session = getSession()) {
            Query<UbicacionEntity> q = session.createQuery(query);
            q.setParameter("estado", estado);

            List<UbicacionEntity> result = q.getResultList();
            if (result != null && !result.isEmpty()) {
                List<Ubicacion> ubicaciones = new ArrayList<>();
                for(UbicacionEntity entity : result) {
                    ubicaciones.add(mapper.map(entity, Ubicacion.class));
                }
                return ubicaciones;
            }
            return null;
        }
    }

    public void save(Ubicacion ubicacion) {
        UbicacionEntity entity = mapper.map(ubicacion, UbicacionEntity.class);
        super.save(entity);
    }


}
