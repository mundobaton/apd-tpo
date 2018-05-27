package edu.uade.apd.tpo.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import edu.uade.apd.tpo.dao.PedidoDao;
import edu.uade.apd.tpo.entity.ClienteEntity;
import edu.uade.apd.tpo.entity.EstadoEntity;
import edu.uade.apd.tpo.entity.ItemPedidoEntity;
import edu.uade.apd.tpo.entity.PedidoEntity;

public class Pedido {

    private Long id;
    private Date fechaPedido;
    private Date fechaEntrega;
    private Date fechaDespacho;
    private List<ItemPedido> items;
    private List<Estado> estados;
    private Factura factura;
    private Envio envio;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(Date fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public Date getFechaDespacho() {
        return fechaDespacho;
    }

    public void setFechaDespacho(Date fechaDespacho) {
        this.fechaDespacho = fechaDespacho;
    }

    public List<ItemPedido> getItems() {
        return items;
    }

    public void setItems(List<ItemPedido> items) {
        this.items = items;
    }

    public List<Estado> getEstados() {
        return estados;
    }

    public void setEstados(List<Estado> estados) {
        this.estados = estados;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public Envio getEnvio() {
        return envio;
    }

    public void setEnvio(Envio envio) {
        this.envio = envio;
    }

    public void agregarItem(Articulo articulo, int cantidad) {
        boolean itemExiste = false;

        for (ItemPedido i : this.items) {
            if (i.getArticulo().getId() == articulo.getId()) {
                i.actualizar(cantidad);
                itemExiste = true;
            }
        }
        if (!itemExiste) {
            ItemPedido item = new ItemPedido();
            item.setArticulo(articulo);
            item.setCantidad(cantidad);
            this.items.add(item);
        }
    }

    public void iniciar() {
        Estado iniciar = new Estado();
        iniciar.setEstado(EstadoPedido.INICIADO);
        iniciar.setFecha(new Date());
        this.setFechaPedido(new Date());
        this.estados.add(iniciar);
    }

    public void preAprobar() {
        Estado preAprobar = new Estado();
        preAprobar.setEstado(EstadoPedido.PREAPROBADO);
        preAprobar.setFecha(new Date());
        this.estados.add(preAprobar);
    }

    public void revision() {
        Estado revision = new Estado();
        revision.setEstado(EstadoPedido.EN_REVISION);
        revision.setFecha(new Date());
        this.estados.add(revision);
    }

    public void aprobar(String motivo) {

        Estado aprobado = new Estado();

        if(this.estados.get(0).equals(EstadoPedido.EN_REVISION)){
            aprobado.setMotivo(motivo);
        }else{
            aprobado.setMotivo("Pedido aprobado por el administrador.");
        }
        aprobado.setEstado(EstadoPedido.APROBADO);
        aprobado.setFecha(new Date());
        this.estados.add(aprobado);
    }

    public void rechazar(String motivo) {
        Estado rechazado = new Estado();
        rechazado.setEstado(EstadoPedido.RECHAZADO);
        rechazado.setFecha(new Date());
        rechazado.setMotivo(motivo);
        this.estados.add(rechazado);
    }

    public void completar() {
        Estado completo = new Estado();
        completo.setEstado(EstadoPedido.COMPLETO);
        completo.setFecha(new Date());
        this.estados.add(completo);

    }

    public void verificado() {
        Estado verificado = new Estado();
        verificado.setEstado(EstadoPedido.VERIFICADO);
        verificado.setFecha(new Date());
        this.estados.add(verificado);

    }

    public void marcarPendiente() {
        Estado pendiente = new Estado();
        pendiente.setEstado(EstadoPedido.PENDIENTE);
        pendiente.setFecha(new Date());
        this.estados.add(pendiente);

    }

    public void alistar(Transportista transportista) {
        Estado listo = new Estado();
        listo.setFecha(new Date());
        listo.setEstado(EstadoPedido.LISTO);
        this.estados.add(listo);
        this.envio.setTransportista(transportista);
    }

    public void enviado() {
        Estado enviado = new Estado();
        enviado.setFecha(new Date());
        enviado.setEstado(EstadoPedido.ENVIADO);
        this.estados.add(enviado);
    }

    public float obtenerTotal() {
        float total = 0;
        for (ItemPedido item : this.items) {
            total += item.calcularSubTotal();
        }
        return total;
    }

    public void guardar() {
        PedidoDao.getInstance().save(this.toEntity());
    }

    public float calcularCostoEnvio() {
        return envio.calcular();
    }

    public static Pedido fromEntity(PedidoEntity entity) {
        Pedido pedido = null;
        if (entity != null) {
            pedido = new Pedido();
            pedido.setId(entity.getId());
            pedido.setFechaPedido(entity.getFechaPedido());
            pedido.setFechaEntrega(entity.getFechaEntrega());
            pedido.setFechaDespacho(entity.getFechaDepacho());
            if (entity.getItems() != null) {
                pedido.setItems(new ArrayList<>());
                for (ItemPedidoEntity ipe : entity.getItems()) {
                    pedido.getItems().add(ItemPedido.fromEntity(ipe));
                }
            }
            if (entity.getEstados() != null) {
                pedido.setEstados(new ArrayList<>());
                for (EstadoEntity estado : entity.getEstados()) {
                    pedido.getEstados().add(Estado.fromEntity(estado));
                }
            }
            pedido.setFactura(Factura.fromEntity(entity.getFactura()));
            pedido.setEnvio(Envio.fromEntity(entity.getEnvio()));

        }
        return pedido;
    }

    public PedidoEntity toEntity() {
        PedidoEntity entity = new PedidoEntity();
        entity.setId(id);
        entity.setFechaPedido(fechaPedido);
        entity.setFechaEntrega(fechaEntrega);
        entity.setFechaDepacho(fechaDespacho);
        if (items != null) {
            entity.setItems(new ArrayList<>());
            for (ItemPedido ip : items) {
                entity.getItems().add(ip.toEntity());
            }
        }
        if (estados != null) {
            entity.setEstados(new ArrayList<>());
            for (Estado e : estados) {
                entity.getEstados().add(e.toEntity());
            }
        }
        entity.setFactura(factura != null ? factura.toEntity() : null);
        entity.setEnvio(envio != null ? envio.toEntity() : null);
        return entity;
    }
}
