package edu.uade.apd.tpo.dao;

import edu.uade.apd.tpo.entity.ClienteEntity;
import edu.uade.apd.tpo.entity.CuentaCorrienteEntity;
import edu.uade.apd.tpo.entity.DomicilioEntity;
import edu.uade.apd.tpo.entity.PedidoEntity;
import edu.uade.apd.tpo.model.CondicionIva;
import edu.uade.apd.tpo.model.Zona;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ClienteDaoTest {

    private ClienteDao dao;

    @Before
    public void setup() {
        dao = ClienteDao.getInstance();
    }

    @Test
    public void testCrearCliente() {
        ClienteEntity ce = new ClienteEntity();
        ce.setEmail("cliente@prueba.com");
        ce.setPassword("sarasa");
        ce.setNombre("fulano");
        ce.setCuil(123L);
        ce.setTelefono("4123-4567");
        ce.setCondIva(CondicionIva.CONS_FINAL);

        CuentaCorrienteEntity cce = new CuentaCorrienteEntity();
        cce.setLimiteCredito(1500);
        cce.setSaldo(10);
        ce.setCuentaCorriente(cce);

        DomicilioEntity de = new DomicilioEntity();
        de.setCalle("una calle me separa");
        de.setNumero(new Long(312));
        de.setCodPostal("1406");
        de.setLocalidad("Capital");
        de.setProvincia("Buenos Aires");
        de.setZona(Zona.CABA);

        ce.setDomicilio(de);
        dao.save(ce);
    }

    @Test
    public void testFindClienteByCuil() {
        ClienteEntity ce = dao.findByCuil(123L);
        Assert.assertNotNull(ce);
    }

    @Test
    public void agregarPedidosCliente() {
        ClienteEntity ce = dao.findByCuil(123L);

        PedidoEntity pe = new PedidoEntity();
        pe.setFechaPedido(new Date());
        pe.setCliente(ce);

        List<PedidoEntity> pedidos = ce.getPedidos();
        if (pedidos == null) {
            pedidos = new ArrayList<>();
        }
        pedidos.add(pe);
        ce.setPedidos(pedidos);
        dao.save(ce);
    }

}
