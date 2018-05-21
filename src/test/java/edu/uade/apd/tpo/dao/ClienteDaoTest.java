package edu.uade.apd.tpo.dao;

import edu.uade.apd.tpo.entity.ClienteEntity;
import edu.uade.apd.tpo.entity.CuentaCorrienteEntity;
import edu.uade.apd.tpo.entity.DomicilioEntity;
import edu.uade.apd.tpo.entity.PedidoEntity;
import edu.uade.apd.tpo.model.CondIva;
import edu.uade.apd.tpo.model.ZonaEnvio;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ClienteDaoTest {

    private ClienteDao dao;

    @Before
    public void setup() {
        this.dao = ClienteDao.getInstance();
    }

    @Test
    public void testSaveCliente() {
        ClienteEntity entity = new ClienteEntity();
        entity.setEmail("cliente7@scratch.com");
        entity.setPassword("pwd");
        entity.setCondIva(CondIva.CONS_FINAL);
        entity.setCuil(12345L);
        entity.setTelefono("4123-5678");
        entity.setNombre("Fulano");

        CuentaCorrienteEntity cuentaCorrienteEntity = new CuentaCorrienteEntity();
        cuentaCorrienteEntity.setLimiteCredito(new Float(1000));
        cuentaCorrienteEntity.setSaldo(new Float(500));
        entity.setCuentaCorriente(cuentaCorrienteEntity);

        DomicilioEntity dom = new DomicilioEntity();
        dom.setCalle("Fake St");
        dom.setCodPostal("1406");
        dom.setLocalidad("Cap Fed");
        dom.setNumero(123);
        dom.setProvincia("Buenos Aires");
        dom.setZona(ZonaEnvio.CABA);
        entity.setDomicilio(dom);

        PedidoEntity pedido = new PedidoEntity();
        pedido.setFechaPedido(new Date());
        pedido.setDomicilio(dom);
        pedido.setCliente(entity);

        dao.save(entity);

    }

}
