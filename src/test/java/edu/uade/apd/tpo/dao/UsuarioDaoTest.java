package edu.uade.apd.tpo.dao;

import edu.uade.apd.tpo.entity.UsuarioEntity;
import edu.uade.apd.tpo.model.Rol;
import org.junit.Before;
import org.junit.Test;

public class UsuarioDaoTest {

    private UsuarioDao dao;

    @Before
    public void setup() {
        this.dao = UsuarioDao.getInstance();
    }

    @Test
    public void testSaveUsuario() {
        UsuarioEntity usuarioEntity = new UsuarioEntity();
        usuarioEntity.setRol(Rol.TODOS);
        usuarioEntity.setEmail("prueba@scratch.com");
        usuarioEntity.setPassword("un password");
        dao.save(usuarioEntity);
    }

}
