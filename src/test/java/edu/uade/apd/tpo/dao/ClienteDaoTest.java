package edu.uade.apd.tpo.dao;

import edu.uade.apd.tpo.entity.ArticuloEntity;
import edu.uade.apd.tpo.entity.ClienteEntity;
import edu.uade.apd.tpo.entity.CuentaCorrienteEntity;
import edu.uade.apd.tpo.entity.DomicilioEntity;
import edu.uade.apd.tpo.entity.EnvioEntity;
import edu.uade.apd.tpo.entity.FacturaEntity;
import edu.uade.apd.tpo.entity.ItemLoteEntity;
import edu.uade.apd.tpo.entity.ItemPedidoEntity;
import edu.uade.apd.tpo.entity.LoteEntity;
import edu.uade.apd.tpo.entity.PedidoEntity;
import edu.uade.apd.tpo.entity.PosicionEntity;
import edu.uade.apd.tpo.entity.RemitoEntity;
import edu.uade.apd.tpo.entity.StockEntity;
import edu.uade.apd.tpo.entity.TransaccionEntity;
import edu.uade.apd.tpo.model.CondicionIva;
import edu.uade.apd.tpo.model.EstadoPosicion;
import edu.uade.apd.tpo.model.FacturaTipo;
import edu.uade.apd.tpo.model.MedioPago;
import edu.uade.apd.tpo.model.Transportista;
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

        DomicilioEntity de = new DomicilioEntity();
        de.setCalle("asd");
        de.setNumero(134L);
        de.setZona(Zona.CABA);
        de.setLocalidad("Capital");
        de.setProvincia("Buenos Aires");
        de.setCodPostal("1406");

        EnvioEntity ee = new EnvioEntity();
        ee.setDomicilio(de);
        ee.setTransportista(Transportista.CORREO_ARGENTINO);

        RemitoEntity re = new RemitoEntity();
        re.setFecha(new Date());
        ee.setRemito(re);
        pe.setEnvio(ee);

        FacturaEntity fe = new FacturaEntity();
        fe.setPedido(pe);
        fe.setFecha(new Date());
        fe.setCostoEnvio(10);
        fe.setTipo(FacturaTipo.A);
        fe.setTotal(20);

        TransaccionEntity te = new TransaccionEntity();
        te.setFecha(new Date());
        te.setImporte(30);
        List<FacturaEntity> facturas = new ArrayList<>();
        facturas.add(fe);
        te.setFacturas(facturas);

        fe.setTransaccion(te);
        pe.setFactura(fe);

        te.setMedioPago(MedioPago.EFECTIVO);

        List<PedidoEntity> pedidos = ce.getPedidos();
        if (pedidos == null) {
            pedidos = new ArrayList<>();
        }

        List<TransaccionEntity> transacciones = ce.getCuentaCorriente().getTransacciones();
        if (transacciones == null) {
            transacciones = new ArrayList<>();
            ce.getCuentaCorriente().setTransacciones(transacciones);
        }
        ce.getCuentaCorriente().getTransacciones().add(te);

        te.setCuentaCorriente(ce.getCuentaCorriente());

        ItemPedidoEntity ipe = new ItemPedidoEntity();
        ArticuloEntity ae = new ArticuloEntity();
        ae.setCodBarras("1234");
        ae.setDescripcion("Una descripcion");
        ae.setUnidad("asasda");
        ae.setPresentacion("Bolsita pequeña");
        ae.setCantCompra(99);
        StockEntity stock = new StockEntity();
        ae.setStock(stock);
        ae.setVolumen(50);
        ae.setPrecio(500);
        ipe.setArticulo(ae);

        if (pe.getItems() == null) {
            pe.setItems(new ArrayList<>());
        }
        ipe.setPedido(pe);
        pe.getItems().add(ipe);

        ItemLoteEntity ile = new ItemLoteEntity();
        ile.setCantidad(50);

        LoteEntity lote = new LoteEntity();
        lote.setCodigo("un codigo");
        lote.setFechaVto(new Date());
        lote.setFechaElaboracion(new Date());
        lote.setArticulo(ae);

        lote.setPosiciones(new ArrayList<>());
        PosicionEntity posicion = new PosicionEntity();
        posicion.setCodUbicacion("una ubicacion");
        posicion.setEstado(EstadoPosicion.DISPONIBLE);
        posicion.setLote(lote);
        posicion.setCantidad(99);
        posicion.setCalle('B');
        posicion.setBloque(12);
        posicion.setEstante(14);
        posicion.setEstanteria(19);
        posicion.setNumero(69);

        lote.getPosiciones().add(posicion);
        ile.setLote(lote);
        if (ipe.getLotes() == null) {
            ipe.setLotes(new ArrayList<>());
        }
        ipe.getLotes().add(ile);

        pedidos.add(pe);
        ce.setPedidos(pedidos);
        dao.save(ce);
    }

}
