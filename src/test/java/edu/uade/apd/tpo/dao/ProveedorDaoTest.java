package edu.uade.apd.tpo.dao;

import edu.uade.apd.tpo.entity.ProveedorEntity;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ProveedorDaoTest {
    private ProveedorDao dao;

    @Before
    public void setup() {
        dao = ProveedorDao.getInstance();
    }

    @Test
    public void testCrearProveedor(){
        ProveedorEntity pe = new ProveedorEntity();
        pe.setCuit("27329616253");
        pe.setNombre("Erica Muñoz");
        pe.setTelefono("1564664452");

        dao.save(pe);
    }

    @Test
    public void testBuscarProvedor(){
        ProveedorEntity pe = dao.findByCuit("27329616253");
        Assert.assertNotNull(pe);
    }

}
