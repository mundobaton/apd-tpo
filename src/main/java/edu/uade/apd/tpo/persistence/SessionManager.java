package edu.uade.apd.tpo.persistence;

import edu.uade.apd.tpo.entity.ArticuloEntity;
import edu.uade.apd.tpo.entity.ClienteEntity;
import edu.uade.apd.tpo.entity.CostoEnvioEntity;
import edu.uade.apd.tpo.entity.CuentaCorrienteEntity;
import edu.uade.apd.tpo.entity.DomicilioEntity;
import edu.uade.apd.tpo.entity.EgresoEntity;
import edu.uade.apd.tpo.entity.EstadoEntity;
import edu.uade.apd.tpo.entity.FacturaEntity;
import edu.uade.apd.tpo.entity.IngresoEntity;
import edu.uade.apd.tpo.entity.ItemLoteEntity;
import edu.uade.apd.tpo.entity.ItemPedidoEntity;
import edu.uade.apd.tpo.entity.LoteEntity;
import edu.uade.apd.tpo.entity.MovimientoEntity;
import edu.uade.apd.tpo.entity.OrdenCompraEntity;
import edu.uade.apd.tpo.entity.PedidoEntity;
import edu.uade.apd.tpo.entity.PosicionEntity;
import edu.uade.apd.tpo.entity.ProveedorEntity;
import edu.uade.apd.tpo.entity.RemitoEntity;
import edu.uade.apd.tpo.entity.StockEntity;
import edu.uade.apd.tpo.entity.TransaccionEntity;
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
            configuration.addAnnotatedClass(CostoEnvioEntity.class);
            configuration.addAnnotatedClass(CuentaCorrienteEntity.class);
            configuration.addAnnotatedClass(DomicilioEntity.class);
            configuration.addAnnotatedClass(EgresoEntity.class);
            configuration.addAnnotatedClass(EstadoEntity.class);
            configuration.addAnnotatedClass(FacturaEntity.class);
            configuration.addAnnotatedClass(IngresoEntity.class);
            configuration.addAnnotatedClass(ItemLoteEntity.class);
            configuration.addAnnotatedClass(ItemPedidoEntity.class);
            configuration.addAnnotatedClass(LoteEntity.class);
            configuration.addAnnotatedClass(MovimientoEntity.class);
            configuration.addAnnotatedClass(OrdenCompraEntity.class);
            configuration.addAnnotatedClass(PedidoEntity.class);
            configuration.addAnnotatedClass(PosicionEntity.class);
            configuration.addAnnotatedClass(ProveedorEntity.class);
            configuration.addAnnotatedClass(RemitoEntity.class);
            configuration.addAnnotatedClass(StockEntity.class);
            configuration.addAnnotatedClass(TransaccionEntity.class);
            configuration.addAnnotatedClass(UsuarioEntity.class);
            configuration.setProperty("hibernate.event.merge.entity_copy_observer", "allow");
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
