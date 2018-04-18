package edu.uade.apd.tpo;

import com.google.inject.Guice;
import com.google.inject.Injector;
import edu.uade.apd.tpo.config.ServiceModule;
import edu.uade.apd.tpo.dao.impl.UsuarioDao;
import edu.uade.apd.tpo.model.Usuario;
import edu.uade.apd.tpo.service.HibernateUtil;
import org.hibernate.Session;

public class App {

    public static void main(String[] args) {
        try {
            Injector injector = Guice.createInjector(new ServiceModule());
            new Server(injector);
            App app = new App();
            app.testHibernate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void testHibernate() {
        Usuario u = new Usuario();
        u.setEmail("dasadda@otro-email.com");
        u.setPassword("qwewqe");

        UsuarioDao usuarioDao = new UsuarioDao();
        usuarioDao.save(u);
    }
}
