package edu.uade.apd.tpo.service.impl;

import edu.uade.apd.tpo.model.Articulo;
import edu.uade.apd.tpo.model.Cliente;
import edu.uade.apd.tpo.model.Domicilio;
import edu.uade.apd.tpo.model.Estado;
import edu.uade.apd.tpo.model.EstadoPedido;
import edu.uade.apd.tpo.model.ItemPedido;
import edu.uade.apd.tpo.model.Pedido;
import edu.uade.apd.tpo.service.PedidoService;

import javax.inject.Singleton;

@Singleton
public class PedidoServiceImpl implements PedidoService {

    @Override
    public Pedido crearPedido(Cliente cliente, Domicilio domicilio) {
        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setDomicilio(domicilio);

        Estado estado = new Estado();
        estado.setMotivo("Creación inicial de pedido");
        estado.setEstado(EstadoPedido.INICIADO);
        pedido.addEstado(estado);

        return pedido;
    }

    @Override
    public void agregarItem(Pedido pedido, Articulo articulo, int cantidad) {
        ItemPedido item = new ItemPedido();
        item.setArticulo(articulo);
        item.setCantidad(cantidad);
        pedido.addItem(item);
    }
}
