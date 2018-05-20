package edu.uade.apd.tpo.controller;

import edu.uade.apd.tpo.dao.impl.FacturaDao;
import edu.uade.apd.tpo.dao.impl.PedidoDao;
import edu.uade.apd.tpo.exception.BusinessException;
import edu.uade.apd.tpo.model.CondIva;
import edu.uade.apd.tpo.model.CostoEnvio;
import edu.uade.apd.tpo.model.Factura;
import edu.uade.apd.tpo.model.MedioPago;
import edu.uade.apd.tpo.model.Pedido;
import edu.uade.apd.tpo.model.Remito;
import edu.uade.apd.tpo.model.TipoFactura;
import edu.uade.apd.tpo.model.Transaccion;
import edu.uade.apd.tpo.model.Transportista;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class SistemaFacturacion {

    private static SistemaFacturacion instance;
    private PedidoDao pedidoDao;
    private FacturaDao facturaDao;

    private SistemaFacturacion() {
        this.pedidoDao = PedidoDao.getInstance();
        this.facturaDao = FacturaDao.getInstance();
    }

    public static SistemaFacturacion getInstance() {
        if (instance == null) {
            instance = new SistemaFacturacion();
        }
        return instance;
    }

    public void facturar(Long pedidoId) {
        // Sucede cuando despacho nos avisa que ya tiene listo el pedido para enviar
        // Emitimos factura y remito
        Random rand = new Random();
        int number = rand.nextInt(3 - 1) + 1;

        Pedido p = this.buscarPedido(pedidoId);
        Factura f = new Factura();
        CostoEnvio ce = new CostoEnvio();
        ce.setZona(p.getCliente().getDomicilio().getZona());
        ce.setTransportista(Transportista.values()[number]);

        f.setFecha(new Date());
        f.setPedido(p);
        f.setCostoEnvio(ce);
        f.setImpuestos(new Float(0.21));

        if (p.getCliente().getCondIva() == CondIva.CONS_FINAL) {
            f.setTipo(TipoFactura.B);
        } else {
            f.setTipo(TipoFactura.A);
        }

        f.guardar();

        // Genero el remito
        Remito r = new Remito();
        r.setFactura(f);
        r.setFecha(new Date());
        r.guardar();

    }

    public float procesarPago(String email, float importe, MedioPago mp, float saldo, float limite) {
        List<Factura> facturas = this.obtenerFacturasImpagasCliente(email);
        float importeRestante = (saldo + limite) + importe;

        // Ya vienen ordenadas desde la db
        //TODO convertir en un bucle while
        for (Factura f : facturas) {
            if (f.getTotal() <= importeRestante) {
                Transaccion t = new Transaccion();
                t.setFecha(new Date());
                t.setImporte(f.getTotal());
                t.setMedioPago(mp);
                t.addFactura(f);
                f.setTransaccion(t);
                f.guardar();
                importeRestante -= t.getImporte();
            }
        }
        // devolvemos el importe que le sobro del pago de las facturas menos el limite
        // que le extendimos
        return (importeRestante - limite);
    }

    public float procesarPago(Long facturaId, float importe, MedioPago mp, float saldo, float limite) throws BusinessException {

        Factura f = buscarFactura(facturaId);

        if(f == null) throw new BusinessException("No se ha encontrado la factura indicada.");

        float importeRestante = saldo + limite + importe;

        if (f.getTotal() <= importeRestante) {
            Transaccion t = new Transaccion();
            t.setFecha(new Date());
            t.setImporte(f.getTotal());
            t.setMedioPago(mp);
            t.addFactura(f);
            f.setTransaccion(t);
            f.guardar();
            importeRestante -= t.getImporte();
        }
        return importeRestante - limite;

    }

    private List<Factura> obtenerFacturasCliente(String email) {
        return facturaDao.obtenerFacturasCliente(email);
    }

    private List<Factura> obtenerFacturasImpagasCliente(String email) {

        List<Factura> facturas = this.obtenerFacturasCliente(email);
        Iterator<Factura> iterator = facturas.iterator();

        while (iterator.hasNext()) {
            Factura f = iterator.next();
            if (f.getTransaccion() != null) {
                iterator.remove();
            }
        }

        return facturas;
    }

    private Factura buscarFactura(Long facturaId) {
        return facturaDao.findById(facturaId);
    }

    private Pedido buscarPedido(Long pedidoId) {
        return pedidoDao.findById(pedidoId);
    }
}
