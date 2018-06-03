package edu.uade.apd.tpo.entity;

<<<<<<< HEAD
import java.util.List;

import edu.uade.apd.tpo.model.CondicionIva;
=======
import edu.uade.apd.tpo.model.CondIva;
import edu.uade.apd.tpo.model.Pedido;
>>>>>>> develop
import edu.uade.apd.tpo.model.Rol;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
<<<<<<< HEAD
=======
import javax.persistence.FetchType;
>>>>>>> develop
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
<<<<<<< HEAD
=======
import java.util.List;
import java.util.Objects;
>>>>>>> develop

@Entity
@Table(name = "clientes")
@PrimaryKeyJoinColumn(name = "cliente_id")
public class ClienteEntity extends UsuarioEntity {

    @Column(name = "nombre")
    private String nombre;
    @Column(name = "cuil")
<<<<<<< HEAD
    private Long cuil;
=======
    private long cuil;
>>>>>>> develop
    @Column(name = "telefono")
    private String telefono;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "domicilio_id")
    private DomicilioEntity domicilio;
<<<<<<< HEAD
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "cond_iva_id")
    private CondicionIva condIva;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cuenta_corriente_id")
    private CuentaCorrienteEntity cuentaCorriente;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "cliente_id")
    private List<PedidoEntity> pedidos;

    public ClienteEntity() {
=======
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
>>>>>>> develop
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

<<<<<<< HEAD
    public DomicilioEntity getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(DomicilioEntity domicilio) {
        this.domicilio = domicilio;
    }

    public CondicionIva getCondIva() {
        return condIva;
    }

    public void setCondIva(CondicionIva condIva) {
=======
    public CondIva getCondIva() {
        return condIva;
    }

    public void setCondIva(CondIva condIva) {
>>>>>>> develop
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

<<<<<<< HEAD

=======
    public DomicilioEntity getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(DomicilioEntity domicilio) {
        this.domicilio = domicilio;
    }
>>>>>>> develop
}
