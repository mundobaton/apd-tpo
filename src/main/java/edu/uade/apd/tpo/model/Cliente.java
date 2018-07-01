package edu.uade.apd.tpo.model;

import edu.uade.apd.tpo.dao.ClienteDao;
import edu.uade.apd.tpo.exception.BusinessException;

import java.util.List;

public class Cliente {

    private Long id;
    private String nombre;
    private Long cuit;
    private String email;
    private String password;
    private CuentaCorriente cuentaCorriente;
    private Domicilio domicilio;
    private List<Pedido> pedidos;

    public Cliente() {

    }

    public Cliente(String email, String nombre, Long cuit, String password, String calle, int numero, String localidad, String provincia, String codPostal, float saldo, float credito) {
        this.email = email;
        this.nombre = nombre;
        this.cuit = cuit;
        this.password = password;
        this.domicilio = new Domicilio(calle, numero, localidad, provincia, codPostal);
        this.cuentaCorriente = new CuentaCorriente(saldo, credito);
    }

    public void facturar(Pedido pedido) throws BusinessException {
        cuentaCorriente.generarFactura(pedido);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getCuit() {
        return cuit;
    }

    public void setCuit(Long cuit) {
        this.cuit = cuit;
    }
}
