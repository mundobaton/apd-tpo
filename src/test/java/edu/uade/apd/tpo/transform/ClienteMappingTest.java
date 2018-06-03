package edu.uade.apd.tpo.transform;

import edu.uade.apd.tpo.dao.ClienteDao;
import edu.uade.apd.tpo.entity.ClienteEntity;
import edu.uade.apd.tpo.model.Cliente;
import org.junit.Assert;
import org.junit.Test;

public class ClienteMappingTest {

    @Test
    public void testMapCliente() {
        ClienteEntity entity = ClienteDao.getInstance().findByEmail("cliente@prueba.com");
        Cliente cli = Cliente.fromEntity(entity);

        Assert.assertNotNull(cli);

    }
}
