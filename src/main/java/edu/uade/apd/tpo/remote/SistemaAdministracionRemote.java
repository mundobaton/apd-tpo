package edu.uade.apd.tpo.remote;

import edu.uade.apd.tpo.controller.SistemaAdministracion;
import edu.uade.apd.tpo.exception.BusinessException;
import edu.uade.apd.tpo.model.Cliente;
import edu.uade.apd.tpo.model.CondicionIva;
import edu.uade.apd.tpo.model.Rol;
import edu.uade.apd.tpo.model.Zona;
import edu.uade.apd.tpo.repository.SistemaAdministracionRepository;
import edu.uade.apd.tpo.repository.exception.RemoteBusinessException;
import edu.uade.apd.tpo.repository.stub.ClienteStub;
import edu.uade.apd.tpo.repository.stub.CondIvaStub;
import edu.uade.apd.tpo.repository.stub.PedidoStub;
import edu.uade.apd.tpo.repository.stub.RolStub;
import edu.uade.apd.tpo.repository.stub.ZonaStub;


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
    public void crearUsuario(String email, String password, RolStub rolStub) throws RemoteBusinessException {
        Rol rol = Rol.fromStub(rolStub);
        try {
            this.controller.crearUsuario(email, password, rol);
        } catch (BusinessException be) {
            throw new RemoteBusinessException(be.getMessage());
        }

    }

    @Override
    public void crearCliente(Long cuil, String email, String password, String nombre, String telefono, String calle, Long num, String cp, String loc, String prov, CondIvaStub condIva, ZonaStub zona, float saldo, float limiteCredito) throws RemoteBusinessException {
        CondicionIva cIva = CondicionIva.fromStub(condIva);
        Zona z = Zona.fromStub(zona);
        try {
            this.controller.crearCliente(cuil, email, password, nombre, telefono, calle, num, cp, loc, prov, cIva, z, saldo, limiteCredito);
        } catch (BusinessException be) {
            throw new RemoteBusinessException(be.getMessage());
        }
    }

    @Override
    public Long crearPedido(Long cuil, String calle, Long num, String cp, String loc, String prov, ZonaStub zona) throws RemoteException {
        Zona z = Zona.fromStub(zona);
        try {
            return this.controller.generarPedido(cuil, calle, num, cp, loc, prov, z).getId();
        } catch (BusinessException be) {
            throw new RemoteBusinessException(be.getMessage());
        }
    }

    @Override
    public void agregarItemPedido(Long pedidoId, Long cuil, Long articuloId, int cant) throws RemoteException {
        try {
            controller.agregarItemPedido(pedidoId, cuil, articuloId, cant);
        } catch (BusinessException be) {
            throw new RemoteBusinessException(be.getMessage());
        }
    }

    @Override
    public void cerrarPedido(Long pedidoId, Long cuil) throws RemoteException {
        try {
            controller.cerrarPedido(pedidoId, cuil);
        } catch (BusinessException be) {
            throw new RemoteBusinessException(be.getMessage());
        }
    }

    @Override
    public void aprobarPedido(Long pedidoId, Long cuil, String motivo) throws RemoteException {
        try {
            controller.aprobarPedido(pedidoId, cuil, motivo);
        } catch (BusinessException be) {
            throw new RemoteBusinessException(be.getMessage());
        }
    }


    @Override
    public void eliminarItemPedido(Long pedidoId, Long articuloId) throws RemoteException {
        try {
            controller.eliminarItemPedido(pedidoId, articuloId);
        } catch (BusinessException be) {
            throw new RemoteBusinessException(be.getMessage());
        }
    }

    @Override
    public void crearArticulo(String codBarras, String descripcion, String presentacion, String unidad, int cantCompra, int volumen, float precio) throws RemoteException {
        controller.crearArticulo(codBarras, descripcion, presentacion, unidad, cantCompra, volumen, precio);
    }

    @Override
    public void procesarPedidosPendientes() throws RemoteException {
        try {
            controller.procesarPedidosPendientesCompraIngresada();
        } catch (BusinessException be) {
            throw new RemoteBusinessException(be.getMessage());
        }

    }

	@Override
	public List<ClienteStub> getClientes() throws RemoteException  {
	    return controller.getClientes().parallelStream().map(c -> c.toStub()).collect(Collectors.toList());
    }
    
	@Override
	public List<PedidoStub> obtenerPedidosPendientes() throws RemoteException {
		return controller.obtenerPedidosPendientes().parallelStream().map(c -> c.toStub()).collect(Collectors.toList());
	}
}
