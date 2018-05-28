package edu.uade.apd.tpo.controller;

import edu.uade.apd.tpo.dao.ClienteDao;
import edu.uade.apd.tpo.dao.FacturaDao;
import edu.uade.apd.tpo.dao.PedidoDao;
import edu.uade.apd.tpo.entity.ClienteEntity;
import edu.uade.apd.tpo.entity.FacturaEntity;
import edu.uade.apd.tpo.entity.PedidoEntity;
import edu.uade.apd.tpo.exception.BusinessException;
import edu.uade.apd.tpo.model.Cliente;
import edu.uade.apd.tpo.model.Factura;
import edu.uade.apd.tpo.model.MedioPago;
import edu.uade.apd.tpo.model.Pedido;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class SistemaFacturacionTest {

    private SistemaFacturacion sistema;

    @Before
    public void setup() {
        sistema = SistemaFacturacion.getInstance();

    }

    @Test
    public void testBuscarFactura() {
        Factura f = sistema.buscarFactura(1L);
        Assert.assertNotNull(f);
    }

    @Test
    public void testFacturar() throws BusinessException {
        PedidoEntity entity = PedidoDao.getInstance().findById(2L);
        Pedido p = Pedido.fromEntity(entity);
        sistema.facturar(p.getId());
    }

    @Test
    public void testProcesarPago() throws BusinessException {
        ClienteEntity c_entity = ClienteDao.getInstance().findByCuil(27329616253L);
        Cliente cli = Cliente.fromEntity(c_entity);
        Factura f = cli.obtenerPedido(45L).getFactura();
        SistemaAdministracion.getInstance().realizarPago(f.getId(), f.getTotal(), MedioPago.EFECTIVO);
    }

    @Test
    public void testProcesarPagoImporte() throws BusinessException{
        ClienteEntity c_entity = ClienteDao.getInstance().findByCuil(2732961625L);
        Cliente cli = Cliente.fromEntity(c_entity);
        sistema.procesarPagoImporte(cli.getCuil(), 5000, MedioPago.EFECTIVO, cli.getCuentaCorriente().getSaldo(), cli.getCuentaCorriente().getLimiteCredito());
    }

    @Test
    public void testObtenerFacturasImpagas() throws BusinessException{
        List<Factura> facturas = sistema.obtenerFacturasImpagas(2732961625L);
        Assert.assertNotNull(facturas);
    }


}
