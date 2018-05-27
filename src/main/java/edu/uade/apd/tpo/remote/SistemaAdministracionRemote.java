package edu.uade.apd.tpo.remote;

import edu.uade.apd.tpo.controller.SistemaAdministracion;
import edu.uade.apd.tpo.controller.SistemaCompras;
import edu.uade.apd.tpo.controller.SistemaDeposito;
import edu.uade.apd.tpo.controller.SistemaFacturacion;
import edu.uade.apd.tpo.dao.UsuarioDao;
import edu.uade.apd.tpo.exception.BusinessException;
import edu.uade.apd.tpo.model.Articulo;
import edu.uade.apd.tpo.model.Cliente;
import edu.uade.apd.tpo.model.CondicionIva;
import edu.uade.apd.tpo.model.CuentaCorriente;
import edu.uade.apd.tpo.model.Domicilio;
import edu.uade.apd.tpo.model.Envio;
import edu.uade.apd.tpo.model.Factura;
import edu.uade.apd.tpo.model.ItemPedido;
import edu.uade.apd.tpo.model.MedioPago;
import edu.uade.apd.tpo.model.Pedido;
import edu.uade.apd.tpo.model.Rol;
import edu.uade.apd.tpo.model.Usuario;
import edu.uade.apd.tpo.model.Zona;
import edu.uade.apd.tpo.repository.SistemaAdministracionRepository;
import edu.uade.apd.tpo.repository.exception.UserNotFoundException;
import edu.uade.apd.tpo.repository.stub.ClienteStub;
import edu.uade.apd.tpo.repository.stub.CondIvaStub;
import edu.uade.apd.tpo.repository.stub.PedidoStub;
import edu.uade.apd.tpo.repository.stub.RolStub;
import edu.uade.apd.tpo.repository.stub.UsuarioStub;
import edu.uade.apd.tpo.repository.stub.ZonaEnvioStub;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Iterator;
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
    public void crearUsuario(String email, String password, RolStub rolStub) throws RemoteException {
        Rol rol = Rol.fromStub(rolStub);
        this.controller.crearUsuario(email, password, rol);
    }

    @Override
    public List<UsuarioStub> getUsuarios() throws RemoteException {
        return controller.getUsuarios();
    }

    @Override
    public List<ClienteStub> getClientes() throws RemoteException {
        return controller.getClientes();
    }

    @Override
    public void actualizarUsuario(UsuarioStub usuarioStub) throws RemoteException {
        Usuario u = Usuario.fromStub(usuarioStub);
        controller.actualizarUsuario(u);
    }

    @Override
    public void crearCliente(String email, String password, String nombre, long cuil, String telefono, CondIvaStub condIva, String calle, int numero, String codPostal, String localidad, String provincia, ZonaEnvioStub zona, float saldo, float limiteCredito) throws RemoteException {
        this.controller.crearCliente(email, password, nombre, cuil, telefono, CondIva.fromStub(condIva), calle, numero, codPostal, localidad, provincia, ZonaEnvio.fromStub(zona), saldo, limiteCredito);
    }

    @Override
    public void generarPedido(Long cuil, String calle, Long num, String codPostal, String localidad, String prov, ZonaStub zona) throws RemoteException {
        this.controller.generarPedido(cuil, calle, num, codPostal, localidad, prov, Zona.fromStub(zona));
    }

    @Override
    public void agregarItemPedido(Long pedidoId, Long articuloId, int cant) throws RemoteException {
        this.controller.agregarItemPedido(pedidoId, articuloId, cant);
    }

    @Override
    public List<PedidoStub> getPedidosPendientes() throws RemoteException {
        return controller.getPedidosPendientes().parallelStream().map(p -> p.toStub()).collect(Collectors.toList());
    }
	
    @Override
    public List<PedidoStub> obtenerPedidosParaAprobar(){
    	return controller.obtenerPedidosParaAprobar();
    }
    
    @Override
    public List<PedidoStub> obtenerPedidoCompletos(){
    	return controller.obtenerPedidoCompletos();
    }
    
    @Override
    public List<PedidoStub> obtenerPedidosACompletar(){
    	return controller.obtenerPedidosACompletar();
    }
    
    @Override
    public ClienteStub buscarCliente(Long cuil){
    	return controller.buscarCliente(cuil);
    }
    
    @Override
    public ClienteStub buscarCliente(String email){
    	return controller.buscarCliente(email);
    }

    @Override
    public List<ArticuloStub> obtenerArticulos(){
    	return controller.obtenerArticulos();
    }

    @Override
	public PedidoStub buscarPedido(Long pedidoId) {
		return controller.buscarPedido(pedidoId);
	}

    @Override
 	public void cerrarPedido(Long pedidoId) {
 		return controller.cerrarPedido(pedidoId);
 	}

    @Override
   	public void aprobarPedido(Long pedidoId) {
   		return controller.aprobarPedido(pedidoId);;
   	}

  
    @Override
   	public void realizarPago(Long facturaId, float importe, MedioPago mp) {
   		return controller.realizarPago(facturaId, importe, mp);
   	}
    
    
    @Override
   	public void realizarPagoimporte(Long cuil, float importe, MedioPago mp) {
   		return controller.realizarPagoImporte(cuil, importe, mp);
   	}
    
    
    @Override
   	public void  rechazarPedido(Long pedidoId, String motivo) {
   		return controller.rechazarPedido(pedidoId, motivo);
   	}
    
    @Override
   	public UsuarioStub login(String email, String password) {
   		return controller.login(email, password);
   	}

    @Override
   	public void  eliminarItemPedido(Long pedidoId, Long articuloId)  {
   		return controller.eliminarItemPedido(pedidoId, articuloId);
   	}

    @Override
    public List<PedidoStub> obtenerPedidosListos(){
    	return controller.obtenerPedidosListos();
    }
	
  

	
}