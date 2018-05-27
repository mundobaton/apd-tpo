package edu.uade.apd.tpo.remote;

import edu.uade.apd.tpo.controller.SistemaAdministracion;
import edu.uade.apd.tpo.model.*;
import edu.uade.apd.tpo.repository.SistemaAdministracionRepository;
import edu.uade.apd.tpo.repository.exception.UserNotFoundException;
import edu.uade.apd.tpo.repository.stub.*;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.stream.Collectors;

public class SistemaAdministracionRemote extends UnicastRemoteObject implements SistemaAdministracionRepository {

    private static SistemaAdministracionRemote instance;
    private SistemaAdministracion controller;

    private SistemaAdministracionRemote() throws RemoteException {
        this.controller = SistemaAdministracion.getInstance();
    }

    public static SistemaAdministracionRemote getInstance() throws RemoteException {
        if (instance == null) {
            instance = new SistemaAdministracionRemote();
        }
        return instance;
    }

    @Override
    public List<UsuarioStub> getUsuarios() {
        return null;
    }

    @Override
    public void actualizarUsuario(UsuarioStub u) {
    }

    @Override
    public List<ClienteStub> getClientes() {
        return null;
    }

    @Override
    public List<PedidoStub> obtenerPedidosParaAprobar() {
        return null;
    }

    @Override
    public ClienteStub buscarCliente(Long cuil) {
        return null;
    }

    @Override
    public ClienteStub buscarCliente(String email) throws RemoteException {
        return null;
    }

    @Override
    public void crearUsuario(String email, String password, RolStub rol) {
    }

    @Override
    public List<ArticuloStub> obtenerArticulos() {
        return null;
    }

    @Override
    public void crearCliente(Long cuil, String email, String password, String nombre, String telefono, String calle,
                             Long num, String cp, String loc, String prov, CondIvaStub condIva, ZonaStub zona, float saldo,
                             float limiteCredito) throws RemoteException {
    }

    @Override
    public PedidoStub generarPedido(Long cuil, String calle, Long num, String cp, String loc, String prov, ZonaStub zona) throws RemoteException {
        return null;
    }

    @Override
    public PedidoStub buscarPedido(Long pedidoId) {
        return null;
    }

    @Override
    public void agregarItemPedido(Long pedidoId, Long articuloId, int cant) throws RemoteException {
    }

    @Override
    public void notificarClienteEstadoPedido(Long pedidoId) throws RemoteException {
    }

    @Override
    public void cerrarPedido(Long pedidoId) throws RemoteException {
    }

    @Override
    public void aprobarPedido(Long pedidoId) throws RemoteException {
    }

    @Override
    public void realizarPago(Long facturaId, float importe, MedioPagoStub mp) throws RemoteException {
    }

    @Override
    public void realizarPagoImporte(Long cuil, float importe, MedioPagoStub mp) throws RemoteException {
    }

    @Override
    public void rechazarPedido(Long pedidoId, String motivo) throws RemoteException {
    }

    @Override
    public UsuarioStub login(String email, String password) throws UserNotFoundException {
        return null;
    }

    @Override
    public void eliminarItemPedido(Long pedidoId, Long articuloId) throws RemoteException {
    }

    @Override
    public List<PedidoStub> obtenerPedidoCompletos() {
        return null;
    }

    @Override
    public List<PedidoStub> obtenerPedidosListos() {
        return null;
    }

    @Override
    public List<PedidoStub> obtenerPedidosACompletar() {
        return null;
    }

}