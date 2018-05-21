package edu.uade.apd.tpo.dao;

import edu.uade.apd.tpo.entity.CuentaCorrienteEntity;
import org.junit.Before;
import org.junit.Test;

public class CuentaCorrienteDaoTest {

    private CuentaCorrienteDao dao;

    @Before
    public void setup() {
        dao = CuentaCorrienteDao.getInstance();
    }

    @Test
    public void testSaveCuentaCorriente() {
        CuentaCorrienteEntity entity = new CuentaCorrienteEntity();
        entity.setSaldo(new Float(150));
        entity.setLimiteCredito(new Float(1000));
    }

}
