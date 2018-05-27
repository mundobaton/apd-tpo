package edu.uade.apd.tpo.dao;

import edu.uade.apd.tpo.entity.ArticuloEntity;
import edu.uade.apd.tpo.entity.OrdenCompraEntity;
import edu.uade.apd.tpo.entity.PedidoEntity;
import edu.uade.apd.tpo.entity.ProveedorEntity;
import edu.uade.apd.tpo.model.EstadoCompra;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

public class OrdenCompraDaoTest {

    private OrdenCompraDao dao;

    @Before
    public void setup() {
        dao = OrdenCompraDao.getInstance();
    }

    @Test
    public void testCrearOrdenCompraPendiente(){
        OrdenCompraEntity oc = new OrdenCompraEntity();
        ArticuloEntity art = ArticuloDaoTest.getInstance().findByCodigo("1234");
        PedidoEntity ped = PedidoDao.getInstance().findById(new Long(10));
        ProveedorEntity prov = ProveedorDao.getInstance().findByCuit("27329616253");

        oc.setArticulo(art);
        oc.setEstado(EstadoCompra.PENDIENTE);
        oc.setFecha(new Date());
        oc.setPedido(ped);
        oc.setProveedor(prov);

        dao.save(oc);

    }

    @Test
    public void testBuscarOrdenCompra(){
        OrdenCompraEntity oc = dao.findById(new Long(1));
        Assert.assertNotNull(oc);
    }

}
