package edu.uade.apd.tpo.model;

import java.util.ArrayList;
import java.util.List;

public class CuentaCorriente {

    private Long id;
    private float saldo;
    private float credito;
    private List<Nota> notas;
    private List<Factura> facturas;

    public CuentaCorriente() {

    }

    public CuentaCorriente(float saldo, float credito) {
        this.saldo = saldo;
        this.credito = credito;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public float getCredito() {
        return credito;
    }

    public void setCredito(float credito) {
        this.credito = credito;
    }

    public List<Nota> getNotas() {
        return notas;
    }

    public void setNotas(List<Nota> notas) {
        this.notas = notas;
    }

    public boolean tieneSaldoDisponible(float importe) {
        return saldo + credito >= importe;
    }

    public void agregarNota(String mensaje, Pedido pedido) {
        Nota nota = new Nota(mensaje, pedido);
        if (this.notas == null) {
            notas = new ArrayList<>();
        }
        notas.add(nota);
    }

    public List<Factura> getFacturas() {
        return facturas;
    }

    public void setFacturas(List<Factura> facturas) {
        this.facturas = facturas;
    }

    public void agregarFactura(Factura factura) {
        if (this.facturas == null) {
            facturas = new ArrayList<>();
        }
        facturas.add(factura);
    }

    public void generarFactura(Pedido pedido) {
        Factura factura = new Factura(pedido);
        this.agregarFactura(factura);
    }
}
