package edu.uade.apd.tpo.model;

import java.util.ArrayList;
import java.util.List;

import edu.uade.apd.tpo.dao.ClienteDao;
import edu.uade.apd.tpo.dao.UsuarioDao;
import edu.uade.apd.tpo.entity.ClienteEntity;
import edu.uade.apd.tpo.entity.PedidoEntity;

public class Cliente extends Usuario {

    private String nombre;
    private Long cuil;
    private String telefono;
    private Domicilio domicilio;
    private CondicionIva condIva;
    private CuentaCorriente cuentaCorriente;
    private List<Pedido> pedidos;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getCuil() {
        return cuil;
    }

    public void setCuil(Long cuil) {
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

    public CondicionIva getCondIva() {
        return condIva;
    }

    public void setCondIva(CondicionIva condIva) {
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

    public Cliente guardar() {
        return Cliente.fromEntity(ClienteDao.getInstance().save(this.toEntity()));
    }

    public static Cliente fromEntity(ClienteEntity entity) {
        Cliente cli = null;
        if (entity != null) {

            cli = new Cliente();
            cli.setId(entity.getId());
            cli.setEmail(entity.getEmail());
            cli.setPassword(entity.getPassword());
            cli.setRol(entity.getRol());
            cli.setNombre(entity.getNombre());
            cli.setCuil(entity.getCuil());
            cli.setTelefono(entity.getTelefono());
            cli.setDomicilio(Domicilio.fromEntity(entity.getDomicilio()));
            cli.setCondIva(entity.getCondIva());
            cli.setCuentaCorriente(CuentaCorriente.fromEntity(entity.getCuentaCorriente()));
            if (entity.getPedidos() != null) {
                cli.setPedidos(new ArrayList<>());
                for (PedidoEntity pe : entity.getPedidos()) {
                    cli.getPedidos().add(Pedido.fromEntity(pe));
                }
            }
        }
        return cli;
    }

    public ClienteEntity toEntity() {
        ClienteEntity entity = new ClienteEntity();
        entity.setId(id);
        entity.setEmail(email);
        entity.setPassword(password);
        entity.setRol(rol);
        entity.setNombre(nombre);
        entity.setCuil(cuil);
        entity.setTelefono(telefono);
        entity.setDomicilio(domicilio != null ? domicilio.toEntity() : null);
        entity.setCondIva(condIva);
        entity.setCuentaCorriente(cuentaCorriente != null ? cuentaCorriente.toEntity() : null);
        if (pedidos != null) {
            entity.setPedidos(new ArrayList<>());
            for (Pedido p : pedidos) {
                entity.getPedidos().add(p.toEntity());
            }
        }

        return entity;
    }

}
