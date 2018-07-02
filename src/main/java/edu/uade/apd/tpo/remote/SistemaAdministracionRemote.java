package edu.uade.apd.tpo.remote;

import edu.uade.apd.tpo.controller.SistemaAdministracion;
import edu.uade.apd.tpo.entity.ClienteEntity;
import edu.uade.apd.tpo.exception.BusinessException;
import edu.uade.apd.tpo.model.Cliente;
import edu.uade.apd.tpo.model.EstadoPedido;
import edu.uade.apd.tpo.model.Pedido;
import edu.uade.apd.tpo.model.Rol;
import edu.uade.apd.tpo.model.Usuario;
import edu.uade.apd.tpo.repository.SistemaAdministracionRepository;
import edu.uade.apd.tpo.repository.dto.ClienteDTO;
import edu.uade.apd.tpo.repository.dto.PedidoDTO;
import edu.uade.apd.tpo.repository.dto.RolDTO;
import edu.uade.apd.tpo.repository.dto.UsuarioDTO;
import edu.uade.apd.tpo.repository.exception.RemoteBusinessException;
import org.modelmapper.ModelMapper;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class SistemaAdministracionRemote extends UnicastRemoteObject implements SistemaAdministracionRepository {

    private static SistemaAdministracionRemote instance;
    private SistemaAdministracion controller;
    private ModelMapper mapper;

    private SistemaAdministracionRemote() throws RemoteException {
        this.controller = SistemaAdministracion.getInstance();
        this.mapper = new ModelMapper();
    }

    public static SistemaAdministracionRemote getInstance() throws RemoteException {
        if (instance == null) {
            instance = new SistemaAdministracionRemote();
        }
        return instance;
    }

    @Override
    public void crearCliente(String email, String nombre, Long cuit, String password, String calle, int numero, String localidad, String provincia, String codPostal, float saldo, float credito) throws RemoteException {
        try {
            controller.crearCliente(email, nombre, cuit, password, calle, numero, localidad, provincia, codPostal, saldo, credito);
        } catch (BusinessException be) {
            throw new RemoteBusinessException(be.getMessage());
        }
    }

    @Override
    public void crearUsuario(String legajo, String password, RolDTO rol) throws RemoteException {
        Rol r = mapper.map(rol, Rol.class);
        try {
            controller.crearUsuario(legajo, password, r);
        } catch (BusinessException be) {
            throw new RemoteBusinessException(be.getMessage());
        }
    }

    @Override
    public ClienteDTO loginCliente(String email, String password) throws RemoteException {
        try {
            Cliente cliente = controller.loginCliente(email, password);
            return mapper.map(cliente, ClienteDTO.class);
        } catch (BusinessException be) {
            throw new RemoteBusinessException(be.getMessage());
        }
    }

    @Override
    public UsuarioDTO loginUsuario(String legajo, String password) throws RemoteException {
        try {
            Usuario usuario = controller.loginUsuario(legajo, password);
            return mapper.map(usuario, UsuarioDTO.class);
        } catch (BusinessException be) {
            throw new RemoteBusinessException(be.getMessage());
        }
    }

    @Override
    public ClienteDTO findClienteById(Long clienteId) throws RemoteException {
        try {
            Cliente cliente = controller.buscarClienteById(clienteId);
            return mapper.map(cliente, ClienteDTO.class);
        } catch (BusinessException be) {
            throw new RemoteBusinessException(be.getMessage());
        }
    }

    @Override
    public Long crearPedido(Long clienteId, String calle, int numero, String localidad, String provincia, String codPostal) throws RemoteException {
        try {
            return controller.crearPedido(clienteId, calle, numero, localidad, provincia, codPostal);
        } catch (BusinessException be) {
            throw new RemoteBusinessException(be.getMessage());
        }
    }

    @Override
    public void agregarItemPedido(Long pedidoId, Long articuloId, int cantidad) throws RemoteException {
        try {
            controller.agregarItemPedido(pedidoId, articuloId, cantidad);
        } catch (BusinessException be) {
            throw new RemoteBusinessException(be.getMessage());
        }
    }

    @Override
    public void finalizarCargaItems(Long pedidoId) throws RemoteException {
        try {
            controller.finalizarCargaItems(pedidoId);
        } catch (BusinessException be) {
            throw new RemoteBusinessException(be.getMessage());
        }
    }

    @Override
    public void aprobarPedido(Long pedidoId, String mensaje) throws RemoteException {
        try {
            controller.aprobarPedido(pedidoId, mensaje);
        } catch (BusinessException be) {
            throw new RemoteBusinessException(be.getMessage());
        }
    }

    @Override
    public void aprobarPedido(Long pedidoId) throws RemoteException {
        try {
            controller.aprobarPedido(pedidoId);
        } catch (BusinessException be) {
            throw new RemoteBusinessException(be.getMessage());
        }
    }

    @Override
    public void rechazarPedido(Long pedidoId, String mensaje) throws RemoteException {
        try {
            controller.rechazarPedido(pedidoId, mensaje);
        } catch (BusinessException be) {
            throw new RemoteBusinessException(be.getMessage());
        }
    }

	@Override
	public PedidoDTO findPedidoById(Long pedidoId) throws RemoteException {
		try {
		 Pedido pedido = controller.findPedidoById(pedidoId);
		 return mapper.map(pedido, PedidoDTO.class);
		}catch(BusinessException be) {
			throw new RemoteBusinessException(be.getMessage());
		}
	}


	@Override
	public List<ClienteDTO> getClientes() throws RemoteException {
		List<Cliente> clientes = controller.getClientes();
		if (clientes != null && !clientes.isEmpty()) {
			List<ClienteDTO> array = new ArrayList<>();
			for (Cliente c : clientes) {
				array.add(mapper.map(c, ClienteDTO.class));
			}
			return array;
		}
		return null;
	}

	
	@Override
	public List<PedidoDTO> obtenerPedidosPendientes() throws RemoteException{
		List<Pedido> pedidos = controller.obtenerPedidosPendientes();
        if (pedidos != null && !pedidos.isEmpty()) {
            List<PedidoDTO> result = new ArrayList<>();
            for (Pedido p : pedidos) {
                result.add(mapper.map(p, PedidoDTO.class));
            }
            return result;
        }
        return null;
	
}
	
}


		



