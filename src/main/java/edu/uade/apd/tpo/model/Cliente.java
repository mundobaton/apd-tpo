package edu.uade.apd.tpo.model;

import edu.uade.apd.tpo.dao.impl.ClienteDao;
import edu.uade.apd.tpo.entity.UsuarioEntity;
import edu.uade.apd.tpo.remote.TransformUtils;
import edu.uade.apd.tpo.repository.stub.ClienteStub;
import edu.uade.apd.tpo.repository.stub.UsuarioStub;

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
        ClienteDao.getInstance().save(this);
    }

    @Override
    public ClienteStub toStub() {
        return TransformUtils.to(this, ClienteStub.class);
    }

    public static Cliente fromStub(ClienteStub stub) {
        return TransformUtils.to(stub, Cliente.class);
    }
}
