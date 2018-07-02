package edu.uade.apd.tpo.model;

import edu.uade.apd.tpo.controller.SistemaCompras;
import edu.uade.apd.tpo.controller.SistemaDeposito;
import edu.uade.apd.tpo.controller.SistemaDespacho;
import edu.uade.apd.tpo.dao.PedidoDao;
import edu.uade.apd.tpo.exception.BusinessException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Pedido {

    private Long id;
    private Domicilio domicilio;
    private List<ItemPedido> items;
    private Cliente cliente;
    private Date fechaPedido;
    private Date fechaDespacho;
    private EstadoPedido estado;
    private Float precioBruto;
    private String transportista;

    public Pedido(String calle, String numero, String localidad, String provincia, String codPostal, Cliente cliente) {
        this.domicilio = new Domicilio(calle, numero, localidad, provincia, codPostal);
        this.cliente = cliente;
        this.estado = EstadoPedido.GENERADO;
    }

    public Pedido() {

    }

    public void facturar() throws BusinessException {
        if (estado != EstadoPedido.A_FACTURAR) {
            throw new BusinessException("El pedido no se encuentra en estado A FACTURAR, estado actual: '" + estado + "'");
        }
        cliente.facturar(this);
        cliente.guardar();

        estado = EstadoPedido.FACTURADO;
        guardar();
    }

    public float calcularPrecioBruto() {
        float precio = 0;
        if (items != null) {
            for (ItemPedido ip : items) {
                precio += ip.getPrecio();
            }
        }
        return precio;
    }

    public void agregarItem(Articulo art, int cant) throws BusinessException {
        if (this.estado != EstadoPedido.GENERADO) {
            throw new BusinessException("El pedido debe encontrarse en estado GENERADO, actual '" + this.estado.toString() + "'");
        }

        if (items == null) {
            items = new ArrayList<>();
        }
        items.add(new ItemPedido(art, cant));
        guardar();
    }

    public void aprobar() throws BusinessException {
        if (this.estado != EstadoPedido.PREAPROBADO && this.estado != EstadoPedido.SALDO_INSUFICIENTE) {
            throw new BusinessException("El estado del pedido es incorrecto, actual: '" + estado.toString() + "'");
        }
        this.estado = EstadoPedido.PENDIENTE;
        this.fechaPedido = new Date();

        guardar();

        //Intento procesar el pedido, si no existe suficiente stock genera ordenes de compra y quedara en estado pendiente.
        //Caso contrario queda en estado completo
        procesar();
    }

    public void rechazar() throws BusinessException {
        if (this.estado != EstadoPedido.PREAPROBADO && this.estado != EstadoPedido.SALDO_INSUFICIENTE) {
            throw new BusinessException("El estado del pedido es incorrecto, actual: '" + estado.toString() + "'");
        }
        this.estado = EstadoPedido.RECHAZADO;
        guardar();
    }

    public void finalizarCargaItems() throws BusinessException {
        if (this.estado != EstadoPedido.GENERADO) {
            throw new BusinessException("El pedido debe encontrarse en estado GENERADO, actual '" + estado.toString() + "'");
        }
        this.precioBruto = calcularPrecioBruto();

        //Validar cuenta corriente del cliente
        this.estado = this.cliente.getCuentaCorriente().tieneSaldoDisponible(precioBruto) ? EstadoPedido.PREAPROBADO : EstadoPedido.SALDO_INSUFICIENTE;
        guardar();
    }

    public void procesar() throws BusinessException {
        if (this.estado != EstadoPedido.PENDIENTE) {
            throw new BusinessException("El pedido debe encontrarse en estado PENDIENTE, actual '" + estado.toString() + "'");
        }
        if (this.items == null || this.items.isEmpty()) {
            throw new BusinessException("Deben cargarse items antes de procesar el pedido");
        }
        boolean esPedidoCompleto = true;
        for (ItemPedido ip : items) {
            if (!ip.getArticulo().contieneStock(ip.getCantidad())) {
                //Checkeo que no exista una OC todavía pendiente para ese articulo
                List<OrdenCompra> ordenesPendientes = SistemaCompras.getInstance().obtenerOrdenesCompraPendientesPorArticulo(ip.getArticulo().getId());
                if (ordenesPendientes == null || ordenesPendientes.isEmpty()) {
                    SistemaCompras.getInstance().generarOrdenCompra(ip, this);
                }
                esPedidoCompleto = false;
            }
        }
        if (esPedidoCompleto) {
            this.estado = EstadoPedido.COMPLETO;
            guardar();
        }
    }

    public void despachar() throws BusinessException {
        if (estado != EstadoPedido.COMPLETO) {
            throw new BusinessException("El pedido no se encuentra COMPLETO, estado actual: '" + estado + "'");
        }
        for (ItemPedido ip : items) {
            for (ItemLote il : ip.getItems()) {
                SistemaDeposito.getInstance().obtenerMercaderia(il);
            }
            Articulo art = ip.getArticulo();
            art.setStock(art.getStock() - ip.getCantidad());
        }
        estado = EstadoPedido.A_FACTURAR;
        fechaDespacho = calcularFechaDespacho();
        transportista = SistemaDespacho.getInstance().getTransportista();
        guardar();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Domicilio getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }

    public List<ItemPedido> getItems() {
        return items;
    }

    public void setItems(List<ItemPedido> items) {
        this.items = items;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Date getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(Date fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public Date getFechaDespacho() {
        return fechaDespacho;
    }

    public void setFechaDespacho(Date fechaDespacho) {
        this.fechaDespacho = fechaDespacho;
    }

    public Pedido guardar() {
        return PedidoDao.getInstance().save(this);
    }

    private Date calcularFechaDespacho() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 7);
        return cal.getTime();
    }

    public Float getPrecioBruto() {
        return precioBruto;
    }

    public void setPrecioBruto(Float precioBruto) {
        this.precioBruto = precioBruto;
    }

    public String getTransportista() {
        return transportista;
    }

    public void setTransportista(String transportista) {
        this.transportista = transportista;
    }
}
