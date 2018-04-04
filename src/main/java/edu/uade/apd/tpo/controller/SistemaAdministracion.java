package edu.uade.apd.tpo.controller;

import edu.uade.apd.tpo.model.Articulo;
import edu.uade.apd.tpo.model.Cliente;
import edu.uade.apd.tpo.model.CondIva;
import edu.uade.apd.tpo.model.Domicilio;
import edu.uade.apd.tpo.model.Pedido;
import edu.uade.apd.tpo.model.Rol;
import edu.uade.apd.tpo.model.Usuario;
import edu.uade.apd.tpo.service.PedidoService;
import edu.uade.apd.tpo.service.UsuarioService;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class SistemaAdministracion {

    private UsuarioService usuarioService;
    private PedidoService pedidoService;
    private List<Usuario> usuarios;
    private List<Pedido> pedidos;

    @Inject
    public SistemaAdministracion(UsuarioService usuarioService, PedidoService pedidoService) {
        this.usuarioService = usuarioService;
        this.pedidoService = pedidoService;
    }

    public Usuario login(String email, String password) {
        return usuarioService.login(email, password);
    }

    public void crearUsuario(String email, String password, Rol rol) {
        usuarioService.crearUsuario(email, password, rol);
    }

    public void crearCliente(String email, String password, Long cuil, String nombre, String apellido, Domicilio domicilio, CondIva condIva) {
        usuarioService.crearCliente(email, password, cuil, nombre, apellido, domicilio, condIva);
    }

    public Pedido crearPedido(Cliente cliente, Domicilio domicilio) {
        return pedidoService.crearPedido(cliente, domicilio);
    }

    public void agregarItemPedido(Pedido pedido, Articulo articulo, int cantidad) {
        pedidoService.agregarItem(pedido, articulo, cantidad);
    }
}
