package edu.uade.apd.tpo.dao;

import edu.uade.apd.tpo.entity.UsuarioEntity;
import edu.uade.apd.tpo.model.Rol;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UsuarioDaoTest {

    private UsuarioDao dao;

    @Before
    public void setup() {
        dao = UsuarioDao.getInstance();
    }

    @Test
    public void testCreateUsuario() {
        UsuarioEntity ue = new UsuarioEntity();
        ue.setEmail("test@prueba.com");
        ue.setPassword("un password seguro");
        ue.setRol(Rol.ADMINISTRACION);
        dao.save(ue);
    }

    @Test
    public void testBuscarUsuario() {
        UsuarioEntity ue = dao.findByEmail("test@prueba.com");
        Assert.assertNotNull(ue);
    }

}
