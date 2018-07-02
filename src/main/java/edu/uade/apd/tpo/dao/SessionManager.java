package edu.uade.apd.tpo.dao;

import edu.uade.apd.tpo.entity.ArticuloEntity;
import edu.uade.apd.tpo.entity.ClienteEntity;
import edu.uade.apd.tpo.entity.CuentaCorrienteEntity;
import edu.uade.apd.tpo.entity.DomicilioEntity;
import edu.uade.apd.tpo.entity.FacturaEntity;
import edu.uade.apd.tpo.entity.ItemLoteEntity;
import edu.uade.apd.tpo.entity.ItemPedidoEntity;
import edu.uade.apd.tpo.entity.NotaEntity;
import edu.uade.apd.tpo.entity.OrdenCompraEntity;
import edu.uade.apd.tpo.entity.PedidoEntity;
import edu.uade.apd.tpo.entity.RemitoEntity;
import edu.uade.apd.tpo.entity.UbicacionEntity;
import edu.uade.apd.tpo.entity.UsuarioEntity;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class SessionManager {

    private static SessionManager instance;
    private SessionFactory sessionFactory;

    private SessionManager() {
        if (sessionFactory == null) {
            Configuration configuration = new Configuration().configure();
            configuration.addAnnotatedClass(ArticuloEntity.class);
            configuration.addAnnotatedClass(ClienteEntity.class);
            configuration.addAnnotatedClass(CuentaCorrienteEntity.class);
            configuration.addAnnotatedClass(DomicilioEntity.class);
            configuration.addAnnotatedClass(FacturaEntity.class);
            configuration.addAnnotatedClass(ItemLoteEntity.class);
            configuration.addAnnotatedClass(ItemPedidoEntity.class);
            configuration.addAnnotatedClass(NotaEntity.class);
            configuration.addAnnotatedClass(OrdenCompraEntity.class);
            configuration.addAnnotatedClass(PedidoEntity.class);
            configuration.addAnnotatedClass(RemitoEntity.class);
            configuration.addAnnotatedClass(UbicacionEntity.class);
            configuration.addAnnotatedClass(UsuarioEntity.class);
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
            sessionFactory = configuration.buildSessionFactory(builder.build());
        }
    }

    public static SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}
