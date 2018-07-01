package edu.uade.apd.tpo.model;

import edu.uade.apd.tpo.dao.FacturaDao;
import edu.uade.apd.tpo.exception.BusinessException;

import java.util.Date;

public class Factura {

    private static final float IMPUESTOS = 0.21f;

    private Long id;
    private char tipo;
    private Pedido pedido;
    private Float total;
    private char estado;
    private Date fecha;

    public Factura() {

    }

    public Factura(Pedido pedido) {
        this.pedido = pedido;
        this.tipo = calcularTipoFactura();
        this.total = calcularTotal();
        this.estado = 'P';
        this.fecha = new Date();
    }

    public void pagar() throws BusinessException {
        if (estado != 'P') {
            throw new BusinessException("La factura debe encontrarse en estado pendiente, actual: '" + Character.toString(estado) + "'");
        }
        if (pedido.getEstado() != EstadoPedido.FACTURADO) {
            throw new BusinessException("El pedido debe estar en estado FACTURADO, actual: '" + pedido.getEstado() + "'");
        }
        CuentaCorriente cuentaCorriente = pedido.getCliente().getCuentaCorriente();
        float saldoRestante = cuentaCorriente.getSaldo() + cuentaCorriente.getCredito() - total;
        if (saldoRestante < 0) {
            throw new BusinessException("Saldo insuficiente para realizar pago");
        }
        cuentaCorriente.setSaldo(cuentaCorriente.getSaldo() - total);
        this.estado = 'C';
        pedido.guardar();
    }

    public char getTipo() {
        return tipo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public void guardar() {
        FacturaDao.getInstance().save(this);
    }

    private char calcularTipoFactura() {
        return 'A';
    }

    private Float calcularTotal() {
        return this.pedido.getPrecioBruto() * IMPUESTOS + this.pedido.getPrecioBruto();
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
