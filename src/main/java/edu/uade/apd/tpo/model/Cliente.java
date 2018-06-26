package edu.uade.apd.tpo.model;

import edu.uade.apd.tpo.dao.ClienteDao;

import java.util.List;

public class Cliente {

    private Long id;
    private String nombreUsuario;
    private String password;
    private CuentaCorriente cuentaCorriente;
    private Domicilio domicilio;
    private List<Pedido> pedidos;

    public Cliente() {

    }

    public Cliente(String nombreUsuario, String password, String calle, int numero, String localidad, String provincia, String codPostal, float saldo, float credito) {
        this.nombreUsuario = nombreUsuario;
        this.password = password;
        this.domicilio = new Domicilio(calle, numero, localidad, provincia, codPostal);
        this.cuentaCorriente = new CuentaCorriente(saldo, credito);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public CuentaCorriente getCuentaCorriente() {
        return cuentaCorriente;
    }

    public void setCuentaCorriente(CuentaCorriente cuentaCorriente) {
        this.cuentaCorriente = cuentaCorriente;
    }

    public Domicilio getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
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
}
