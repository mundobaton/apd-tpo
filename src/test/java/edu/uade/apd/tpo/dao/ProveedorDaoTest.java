package edu.uade.apd.tpo.dao;

import edu.uade.apd.tpo.entity.ProveedorEntity;
import org.junit.Before;
import org.junit.Test;

public class ProveedorDaoTest {

    private ProveedorDao proveedorDao;

    @Before
    public void setup() {
        this.proveedorDao = ProveedorDao.getInstance();
    }

    @Test
    public void testAltaProveedorOk() {
        ProveedorEntity pe = new ProveedorEntity();
        pe.setNombre("Uno baratito");
        pe.setTelefono("4123-4444");
        pe.setCuit("12312313");
        proveedorDao.save(pe);
    }

    @Test
    public void buscarProveedorOk() {
        ProveedorEntity p = proveedorDao.findById(new Integer(1));
        System.out.println(p);
    }

}
