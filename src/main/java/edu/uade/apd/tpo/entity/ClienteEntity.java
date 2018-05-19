package edu.uade.apd.tpo.entity;

import edu.uade.apd.tpo.model.CondIva;
import edu.uade.apd.tpo.model.Pedido;
import edu.uade.apd.tpo.model.Rol;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "clientes")
@PrimaryKeyJoinColumn(name = "cliente_id")
public class ClienteEntity extends UsuarioEntity {

    @Column(name = "nombre")
    private String nombre;
    @Column(name = "cuil")
    private long cuil;
    @Column(name = "telefono")
    private String telefono;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "domicilio_id")
    private DomicilioEntity domicilio;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cuenta_corriente_id")
    private CuentaCorrienteEntity cuentaCorriente;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente_id")
    private List<PedidoEntity> pedidos;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "condicion_iva")
    private CondIva condIva;

    public ClienteEntity() {
        super();
        this.rol = Rol.CLIENTE;
    }

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

    public CondIva getCondIva() {
        return condIva;
    }

    public void setCondIva(CondIva condIva) {
        this.condIva = condIva;
    }

    public CuentaCorrienteEntity getCuentaCorriente() {
        return cuentaCorriente;
    }

    public void setCuentaCorriente(CuentaCorrienteEntity cuentaCorriente) {
        this.cuentaCorriente = cuentaCorriente;
    }

    public List<PedidoEntity> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<PedidoEntity> pedidos) {
        this.pedidos = pedidos;
    }

    public DomicilioEntity getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(DomicilioEntity domicilio) {
        this.domicilio = domicilio;
    }
}
