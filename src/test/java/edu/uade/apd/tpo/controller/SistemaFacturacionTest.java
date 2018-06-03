package edu.uade.apd.tpo.controller;

<<<<<<< HEAD
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
        ClienteEntity c_entity = ClienteDao.getInstance().findByCuil(27329616253L);
        Cliente cli = Cliente.fromEntity(c_entity);
        SistemaAdministracion.getInstance().realizarPagoImporte(cli.getCuil(), 5000, MedioPago.EFECTIVO);
    }

    @Test
    public void testObtenerFacturasImpagas() throws BusinessException{
        List<Factura> facturas = sistema.obtenerFacturasImpagas(2732961625L);
        Assert.assertNotNull(facturas);
    }


=======
import edu.uade.apd.tpo.dao.impl.ClienteDao;
import edu.uade.apd.tpo.exception.BusinessException;
import edu.uade.apd.tpo.model.Cliente;
import edu.uade.apd.tpo.model.MedioPago;
import edu.uade.apd.tpo.model.Usuario;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SistemaFacturacionTest {

    private SistemaFacturacion sistemaFacturacion = SistemaFacturacion.getInstance();
    private static final Logger logger = LoggerFactory.getLogger(SistemaAdministracion.class);

    @Test
    public void testProcesarPagoFactura() throws BusinessException {

        Cliente c = ClienteDao.getInstance().findByEmail("erikannunez@gmail.com");

        Long facturaId = new Long(10);
        Float importe = new Float(2000);

        sistemaFacturacion.procesarPago(facturaId, importe, MedioPago.EFECTIVO, c.getCuentaCorriente().getSaldo(), c.getCuentaCorriente().getLimiteCredito());

    }

    @Test
    public void testProcesarPagoImporte(){

        Cliente c = ClienteDao.getInstance().findByEmail("erikannunez@gmail.com");
        sistemaFacturacion.procesarPago(c.getEmail(), new Float(2000), MedioPago.EFECTIVO,  c.getCuentaCorriente().getSaldo(), c.getCuentaCorriente().getLimiteCredito());

    }

    @Test
    public void testFacturar(){
        sistemaFacturacion.facturar(new Long(1));
    }

>>>>>>> develop
}
