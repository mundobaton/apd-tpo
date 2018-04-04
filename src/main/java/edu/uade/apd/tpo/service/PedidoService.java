package edu.uade.apd.tpo.service;

import edu.uade.apd.tpo.model.Articulo;
import edu.uade.apd.tpo.model.Cliente;
import edu.uade.apd.tpo.model.Domicilio;
import edu.uade.apd.tpo.model.Pedido;

public interface PedidoService {

    Pedido crearPedido(Cliente cliente, Domicilio domicilio);

    void agregarItem(Pedido pedido, Articulo articulo, int cantidad);

}
