package edu.uade.apd.tpo.model;

import edu.uade.apd.tpo.dao.ClienteDao;
import edu.uade.apd.tpo.entity.ClienteEntity;
import edu.uade.apd.tpo.entity.PedidoEntity;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends Usuario {

    private String nombre;
    private long cuil;
    private String telefono;
    private Domicilio domicilio;
    private CondIva condIva;
    private CuentaCorriente cuentaCorriente;
    private List<Pedido> pedidos;

    public Cliente() {
        this.rol = Rol.CLIENTE;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public long getCuil() {
        return cuil;
    }

    public void setCuil(long cuil) {
        this.cuil = cuil;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Domicilio getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }

    public CondIva getCondIva() {
        return condIva;
    }

    public void setCondIva(CondIva condIva) {
        this.condIva = condIva;
    }

    public CuentaCorriente getCuentaCorriente() {
        return cuentaCorriente;
    }

    public void setCuentaCorriente(CuentaCorriente cuentaCorriente) {
        this.cuentaCorriente = cuentaCorriente;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public void guardar() {
        ClienteDao.getInstance().save(this.toEntity());
    }

    public ClienteEntity toEntity() {
        ClienteEntity entity = new ClienteEntity();
        entity.setId(this.id);
        entity.setEmail(this.email);
        entity.setPassword(this.password);
        entity.setRol(this.rol);
        entity.setNombre(this.nombre);
        entity.setCuil(this.cuil);
        entity.setTelefono(this.telefono);
        entity.setDomicilio(this.domicilio.toEntity());
        entity.setCondIva(this.condIva);
        entity.setCuentaCorriente(cuentaCorriente.toEntity());
        if (this.pedidos != null) {
            List<PedidoEntity> pedidoEntities = new ArrayList<>();
            for (Pedido pedido : this.pedidos) {
                pedidoEntities.add(pedido.toEntity());
            }
            entity.setPedidos(pedidoEntities);
        }
        return entity;
    }

    public static Cliente fromEntity(ClienteEntity entity) {
        Cliente cliente = null;
        if (entity != null) {
            cliente = new Cliente();
            cliente.setId(entity.getId());
            cliente.setEmail(entity.getEmail());
            cliente.setPassword(entity.getPassword());
            cliente.setRol(entity.getRol());
            cliente.setNombre(entity.getNombre());
            cliente.setCuil(entity.getCuil());
            cliente.setTelefono(entity.getTelefono());
            cliente.setDomicilio(entity.getDomicilio() == null ? null : Domicilio.fromEntity(entity.getDomicilio()));
            cliente.setCondIva(entity.getCondIva());
            cliente.setCuentaCorriente(entity.getCuentaCorriente() == null ? null : CuentaCorriente.fromEntity(entity.getCuentaCorriente()));
            if (entity.getPedidos() != null) {
                List<Pedido> pedidos = new ArrayList<>();
                for (PedidoEntity pe : entity.getPedidos()) {
                    pedidos.add(Pedido.fromEntity(pe));
                }
                cliente.setPedidos(pedidos);

            }
        }

        return cliente;
    }
}