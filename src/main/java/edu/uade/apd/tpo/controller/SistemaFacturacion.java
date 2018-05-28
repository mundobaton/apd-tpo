package edu.uade.apd.tpo.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.uade.apd.tpo.dao.FacturaDao;
import edu.uade.apd.tpo.dao.RemitoDao;
import edu.uade.apd.tpo.entity.FacturaEntity;
import edu.uade.apd.tpo.exception.BusinessException;
import edu.uade.apd.tpo.model.Cliente;
import edu.uade.apd.tpo.model.CondicionIva;
import edu.uade.apd.tpo.model.Factura;
import edu.uade.apd.tpo.model.FacturaTipo;
import edu.uade.apd.tpo.model.MedioPago;
import edu.uade.apd.tpo.model.Pedido;
import edu.uade.apd.tpo.model.Remito;
import edu.uade.apd.tpo.model.Transaccion;

public class SistemaFacturacion {

    private static SistemaFacturacion instance;
    private FacturaDao facturaDao;
    private RemitoDao remitoDao;

    private SistemaFacturacion() {
        this.facturaDao = FacturaDao.getInstance();
        this.remitoDao = RemitoDao.getInstance();
    }

    public static SistemaFacturacion getInstance() {
        if (instance == null) {
            instance = new SistemaFacturacion();
        }
        return instance;
    }

    public Factura buscarFactura(Long facturaId) {
        FacturaEntity entity = facturaDao.getInstance().findById(facturaId);
        Factura factura = Factura.fromEntity(entity);
        return factura;
    }

    public void facturar(Long pedidoId) throws BusinessException {
        Cliente cliente = SistemaAdministracion.getInstance().obtenerClientePorPedido(pedidoId);
        if (cliente == null) throw new BusinessException("No existe el cliente asociado.");

        Pedido pedido = cliente.obtenerPedido(pedidoId);
        if (pedido == null) throw new BusinessException("No existe el pedido nro. " + pedidoId);

        Factura factura = new Factura();
        factura.setFecha(new Date());

        if (cliente.getCondIva() == CondicionIva.CONS_FINAL) {
            factura.setTipo(FacturaTipo.B);
        } else {
            factura.setTipo(FacturaTipo.A);
        }

        float costoEnvio = pedido.calcularCostoEnvio();
        float subTotal = pedido.obtenerTotal();
        float impuesto = subTotal * Factura.getIMPUESTOS();
        float total = subTotal + costoEnvio + impuesto;

        factura.setCostoEnvio(costoEnvio);
        factura.setTotal(total);

        pedido.setFactura(factura);

        Remito remito = new Remito();
        remito.setFecha(new Date());

        pedido.getEnvio().setRemito(remito);
        pedido.setFechaEntrega(new Date());

        cliente.guardar();

    }

    public void procesarPago(Long facturaId, float importe, MedioPago mp) throws BusinessException {
        Cliente cli = SistemaAdministracion.getInstance().obtenerClientePorFactura(facturaId);
        if (cli == null) throw new BusinessException("No existe cliente asociado al nro. de factura " + facturaId);

        Factura factura = cli.obtenerFactura(facturaId);
        if (factura == null) throw new BusinessException("No existe la factura solicitada.");

        float saldo = cli.getCuentaCorriente().getSaldo();
        float limite = cli.getCuentaCorriente().getLimiteCredito();
        float importeRestante = saldo + limite + importe;
        float total = factura.getTotal();

        if (total <= importeRestante) {
            Transaccion transaccion = new Transaccion();
            transaccion.setFacturas(new ArrayList<>());
            transaccion.setFecha(new Date());
            transaccion.setImporte(total);
            transaccion.setMedioPago(mp);
            transaccion.agregarFactura(factura);

            if (cli.getCuentaCorriente().getTransacciones() == null) {
                cli.getCuentaCorriente().setTransacciones(new ArrayList<>());
            }

            cli.getCuentaCorriente().getTransacciones().add(transaccion);
            importeRestante = importeRestante - total;
        } else {
            throw new BusinessException("Saldo insuficiente para abonar la factura.");
        }

        cli.getCuentaCorriente().setSaldo(importeRestante - limite);
        cli.guardar();
    }

    public void procesarPagoImporte(Long cuil, float importe, MedioPago mp) throws BusinessException {
        Cliente cli = SistemaAdministracion.getInstance().buscarCliente(cuil);
        if(cli == null) throw new BusinessException("Cliente no encontrado");

        List<Factura> facturas = obtenerFacturasImpagas(cuil);

        float saldo = cli.getCuentaCorriente().getSaldo();
        float limiteCred = cli.getCuentaCorriente().getLimiteCredito();
        float importeRestante = saldo + limiteCred + importe;
        float importeAbonado = 0;

        Transaccion transaccion = new Transaccion();
        transaccion.setFacturas(new ArrayList<>());
        transaccion.setFecha(new Date());
        transaccion.setMedioPago(mp);

        for (Factura factura : facturas) {
            float total = factura.getTotal();
            if (total <= importeRestante) {
                importeAbonado += total;
                transaccion.agregarFactura(factura);
                importeRestante -= total;
            }
        }
        transaccion.setImporte(importeAbonado);

        if(cli.getCuentaCorriente().getTransacciones() == null) cli.getCuentaCorriente().setTransacciones(new ArrayList<>());
        cli.getCuentaCorriente().getTransacciones().add(transaccion);
        cli.getCuentaCorriente().setSaldo(importeRestante - limiteCred);
        cli.guardar();
    }

    public List<Factura> obtenerFacturasImpagas(Long cuil) throws BusinessException {
        Cliente cliente = SistemaAdministracion.getInstance().buscarCliente(cuil);
        if (cliente == null) throw new BusinessException("Cliente no encontrado!");
        List<Pedido> pedidos = cliente.getPedidos();
        if (pedidos == null) throw new BusinessException("El cliente no tiene pedidos!");
        List<Transaccion> transacciones = cliente.getCuentaCorriente().getTransacciones();
        if (transacciones == null) cliente.getCuentaCorriente().setTransacciones(new ArrayList<>());
        List<Factura> facturasImpagas = new ArrayList();
        //Agregamos todas las facturas del cliente (pagas e impagas)
        for (Pedido p : pedidos) {
            facturasImpagas.add(p.getFactura());
        }
        //Recorremos las facturas pagas y las quitamos de la lista de impagas
        for (Transaccion t : transacciones) {
            if (t.getFacturas() != null) {
                for (Factura f : t.getFacturas()) {
                    facturasImpagas.remove(f);
                }
            }
        }

        return facturasImpagas;
    }



}
