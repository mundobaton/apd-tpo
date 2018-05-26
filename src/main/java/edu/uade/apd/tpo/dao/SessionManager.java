package edu.uade.apd.tpo.dao;

import edu.uade.apd.tpo.entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class SessionManager {

    private static SessionManager instance;
    private SessionFactory sessionFactory;

    private SessionManager() {
        if (sessionFactory == null) {
            Configuration configuration = new Configuration().configure();
            configuration.addAnnotatedClass(UsuarioEntity.class);
            configuration.addAnnotatedClass(ClienteEntity.class);
            configuration.addAnnotatedClass(DomicilioEntity.class);
            configuration.addAnnotatedClass(CuentaCorrienteEntity.class);
            configuration.addAnnotatedClass(PedidoEntity.class);
            configuration.addAnnotatedClass(EnvioEntity.class);
            configuration.addAnnotatedClass(RemitoEntity.class);
            configuration.addAnnotatedClass(FacturaEntity.class);
            configuration.addAnnotatedClass(TransaccionEntity.class);
            configuration.addAnnotatedClass(ItemPedidoEntity.class);
            configuration.addAnnotatedClass(ArticuloEntity.class);
            configuration.addAnnotatedClass(StockEntity.class);
            configuration.addAnnotatedClass(ItemLoteEntity.class);
            configuration.addAnnotatedClass(LoteEntity.class);
            configuration.addAnnotatedClass(PosicionEntity.class);
            configuration.addAnnotatedClass(ProveedorEntity.class);
            configuration.addAnnotatedClass(OrdenCompraEntity.class);
            configuration.addAnnotatedClass(ItemPosicionEntity.class);
            configuration.setProperty("hibernate.enable_lazy_load_no_trans", "true");
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
